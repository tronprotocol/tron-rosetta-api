package org.tron.api;

import com.google.protobuf.InvalidProtocolBufferException;

import java.lang.Error;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.tron.common.Default;
import org.tron.common.utils.Base58;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.StringUtil;
import org.tron.core.ChainBaseManager;
import org.tron.core.capsule.BlockBalanceTraceCapsule;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.capsule.ContractCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.exception.BadItemException;
import org.tron.core.exception.ItemNotFoundException;
import org.tron.core.store.BalanceTraceStore;
import org.tron.model.*;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.BalanceContract.TransactionBalanceTrace;

import static org.tron.common.utils.StringUtil.encode58Check;

@Controller
@RequestMapping("${openapi.rosetta.base-path:}")
public class BlockApiController implements BlockApi {
  @Autowired
  private ChainBaseManager chainBaseManager;

  private Block genesisBlock;

  private BlockBalanceTraceCapsule genesisBlockBalanceTrace;

  private final NativeWebRequest request;

  @PostConstruct
  private void initGenesis() {
    BlockCapsule genesis = chainBaseManager.getGenesisBlock();
    BlockBalanceTraceCapsule genesisBlockBalanceTraceCapsule = new BlockBalanceTraceCapsule(genesis);
    List<TransactionCapsule> transactionCapsules = genesis.getTransactions();
    for (TransactionCapsule transactionCapsule : transactionCapsules) {
      BalanceContract.TransferContract transferContract = getTransferContract(transactionCapsule);
      TransactionBalanceTrace.Operation from = TransactionBalanceTrace.Operation.newBuilder()
          .setOperationIdentifier(0)
          .setAddress(transferContract.getOwnerAddress())
          .setAmount(String.valueOf(0))
          .build();
      TransactionBalanceTrace.Operation to = TransactionBalanceTrace.Operation.newBuilder()
          .setOperationIdentifier(1)
          .setAddress(transferContract.getToAddress())
          .setAmount(String.valueOf(transferContract.getAmount()))
          .build();

      TransactionBalanceTrace transactionBalanceTrace =
          TransactionBalanceTrace.newBuilder()
              .setTransactionIdentifier(transactionCapsule.getTransactionId().getByteString())
              .setType(Protocol.Transaction.Contract.ContractType.TransferContract.name())
              .setStatus(Protocol.Transaction.Result.contractResult.SUCCESS.name())
//              .addOperation(from)
//              .addOperation(to)
              .build();
      genesisBlockBalanceTraceCapsule.addTransactionBalanceTrace(transactionBalanceTrace);
    }

    genesisBlockBalanceTrace = genesisBlockBalanceTraceCapsule;
    genesisBlock = convert(genesisBlockBalanceTraceCapsule);
  }

  private Block convert(BlockBalanceTraceCapsule blockBalanceTraceCapsule) {
    Block block = new Block();
    BalanceContract.BlockBalanceTrace.BlockIdentifier blockIdentifierPB
        = blockBalanceTraceCapsule.getInstance().getBlockIdentifier();
    BlockIdentifier blockIdentifier = new BlockIdentifier();
    blockIdentifier.hash(ByteArray.toHexString(blockIdentifierPB.getHash().toByteArray()))
        .index(blockIdentifierPB.getNumber());
    block.setBlockIdentifier(blockIdentifier);

    BalanceContract.BlockBalanceTrace.BlockIdentifier parentBlockIdentifierPB
        = blockBalanceTraceCapsule.getInstance().getParentBlockIdentifier();
    BlockIdentifier parentBlockIdentifier = new BlockIdentifier();
    parentBlockIdentifier.hash(ByteArray.toHexString(parentBlockIdentifierPB.getHash().toByteArray()))
        .index(parentBlockIdentifierPB.getNumber());
    block.setParentBlockIdentifier(parentBlockIdentifier);

    block.setTimestamp(blockBalanceTraceCapsule.getInstance().getTimestamp());

    for (TransactionBalanceTrace transactionBalanceTrace : blockBalanceTraceCapsule.getInstance().getTransactionBalanceTraceList()) {
      Transaction transaction = new Transaction()
          .transactionIdentifier(new TransactionIdentifier()
              .hash(ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray())));

      for (TransactionBalanceTrace.Operation operation : transactionBalanceTrace.getOperationList()) {
        Operation op = new Operation()
            .operationIdentifier(new OperationIdentifier().index(operation.getOperationIdentifier()))
            .account(new AccountIdentifier().address(StringUtil.encode58Check(operation.getAddress().toByteArray())))
            .amount(new Amount().currency(Default.CURRENCY).value(operation.getAmount()))
            .type(transactionBalanceTrace.getType())
            .status(transactionBalanceTrace.getStatus());

        transaction.addOperationsItem(op);
      }
      block.addTransactionsItem(transaction);
    }

    return block;
  }

  public BalanceContract.TransferContract getTransferContract(TransactionCapsule transactionCapsule) {
    try {
      return transactionCapsule
          .getInstance()
          .getRawData()
          .getContract(0)
          .getParameter()
          .unpack(BalanceContract.TransferContract.class);
    } catch (InvalidProtocolBufferException e) {
      return null;
    }
  }

  @org.springframework.beans.factory.annotation.Autowired
  public BlockApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }


  /**
   * POST /block : Get a Block
   * Get a block by its Block Identifier. If transactions are returned in the same call to the node as fetching the block, the response should include these transactions in the Block object. If not, an array of Transaction Identifiers should be returned so /block/transaction fetches can be done to get all transaction information.
   *
   * @param blockRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Get a Block", nickname = "block", notes = "Get a block by its Block Identifier. If transactions are returned in the same call to the node as fetching the block, the response should include these transactions in the Block object. If not, an array of Transaction Identifiers should be returned so /block/transaction fetches can be done to get all transaction information.", response = BlockResponse.class, tags = {"Block",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = BlockResponse.class),
      @ApiResponse(code = 200, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/block",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<BlockResponse> block(@ApiParam(value = "", required = true) @Valid @RequestBody BlockRequest blockRequest) {
    AtomicInteger statusCode = new AtomicInteger(200);

    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          BlockResponse blockResponse = new BlockResponse();
          String returnString = "";
          ObjectMapper mapper = new ObjectMapper();
          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          org.tron.model.Error error = new org.tron.model.Error();

          try {
            Long blockIndex = blockRequest.getBlockIdentifier().getIndex();
            String blockHash = blockRequest.getBlockIdentifier().getHash();

            org.tron.model.Block rstBlock = new org.tron.model.Block();
            BlockCapsule tronBlock = null;
            BlockCapsule tronBlockParent = null;

            if (blockIndex != null &&
                blockIndex > chainBaseManager.getDynamicPropertiesStore().getLatestSolidifiedBlockNum()) {
              throw new ItemNotFoundException("request block index > solidified index");
            }

            if (blockIndex != null && blockIndex == 0) {
              blockResponse.setBlock(genesisBlock);
            } else {
              //1. get block
              if (null != blockIndex) {
                tronBlock = chainBaseManager.getBlockByNum(blockIndex);
              } else if (null != blockHash) {
                tronBlock = chainBaseManager.getBlockStore()
                    .get(ByteString.copyFrom(ByteArray.fromHexString(blockHash)).toByteArray());
              } else {
                tronBlock = chainBaseManager.getBlockStore().getBlockByLatestNum(1).get(0);
              }

              if (null != tronBlock && tronBlock.getNum() > 0) {
                tronBlockParent = chainBaseManager.getBlockById(tronBlock.getParentHash());
              } else {
                tronBlockParent = tronBlock;
              }

              //2. set block info
              rstBlock.setBlockIdentifier(
                  new BlockIdentifier()
                      .index(tronBlock.getNum())
                      .hash(ByteArray.toHexString(tronBlock.getBlockId().getBytes())));
              rstBlock.setParentBlockIdentifier(
                  new BlockIdentifier()
                      .index(tronBlockParent.getNum())
                      .hash(ByteArray.toHexString(tronBlockParent.getBlockId().getBytes())));
              rstBlock.setTimestamp(tronBlock.getTimeStamp());

              //3. set tx info
              List<org.tron.model.Transaction> rstTxs = Lists.newArrayList();
              BlockBalanceTraceCapsule blockBalanceTraceCapsule =
                  chainBaseManager.getBalanceTraceStore()
                      .getBlockBalanceTrace(tronBlock.getBlockId());
              if (null == blockBalanceTraceCapsule) {
                throw new ItemNotFoundException("block balance info not find");
              }
              BalanceContract.BlockBalanceTrace blockBalanceTrace =
                  blockBalanceTraceCapsule.getInstance();

              List<BalanceContract.TransactionBalanceTrace> tronTxs = blockBalanceTrace.getTransactionBalanceTraceList();
              for (BalanceContract.TransactionBalanceTrace tronTx : tronTxs) {
                //1. set tx
                org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
                    .transactionIdentifier(new org.tron.model.TransactionIdentifier()
                        .hash(ByteArray.toHexString(tronTx.getTransactionIdentifier().toByteArray())));
                //2. set operations
                List<BalanceContract.TransactionBalanceTrace.Operation> operations = tronTx.getOperationList();
                for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
                  rstTx.addOperationsItem(new org.tron.model.Operation()
                      .operationIdentifier(new OperationIdentifier().index(op.getOperationIdentifier()))
                      .type(tronTx.getType())
                      .status(tronTx.getStatus())
                      .amount(new Amount().currency(Default.CURRENCY).value(op.getAmount()))
                      .account(new AccountIdentifier().address(encode58Check(op.getAddress().toByteArray()))));
                }

                rstTxs.add(rstTx);
              }
              rstBlock.setTransactions(rstTxs);

              //4. response
              blockResponse.setBlock(rstBlock);
            }

            returnString = mapper.writeValueAsString(blockResponse);
          } catch (java.lang.Error | ItemNotFoundException | BadItemException | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(500);
            error.setCode(100);
            error.setMessage("error:" + e.getMessage());
            error.setRetriable(false);
            returnString = JSON.toJSONString(error);
          }
          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    });

    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));
  }


  /**
   * POST /block/transaction : Get a Block Transaction
   * Get a transaction in a block by its Transaction Identifier. This endpoint should only be used when querying a node for a block does not return all transactions contained within it. All transactions returned by this endpoint must be appended to any transactions returned by the /block method by consumers of this data. Fetching a transaction by hash is considered an Explorer Method (which is classified under the Future Work section). Calling this endpoint requires reference to a BlockIdentifier because transaction parsing can change depending on which block contains the transaction. For example, in Bitcoin it is necessary to know which block contains a transaction to determine the destination of fee payments. Without specifying a block identifier, the node would have to infer which block to use (which could change during a re-org). Implementations that require fetching previous transactions to populate the response (ex: Previous UTXOs in Bitcoin) may find it useful to run a cache within the Rosetta server in the /data directory (on a path that does not conflict with the node).
   *
   * @param blockTransactionRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Get a Block Transaction", nickname = "blockTransaction", notes = "Get a transaction in a block by its Transaction Identifier. This endpoint should only be used when querying a node for a block does not return all transactions contained within it. All transactions returned by this endpoint must be appended to any transactions returned by the /block method by consumers of this data. Fetching a transaction by hash is considered an Explorer Method (which is classified under the Future Work section). Calling this endpoint requires reference to a BlockIdentifier because transaction parsing can change depending on which block contains the transaction. For example, in Bitcoin it is necessary to know which block contains a transaction to determine the destination of fee payments. Without specifying a block identifier, the node would have to infer which block to use (which could change during a re-org). Implementations that require fetching previous transactions to populate the response (ex: Previous UTXOs in Bitcoin) may find it useful to run a cache within the Rosetta server in the /data directory (on a path that does not conflict with the node).", response = BlockTransactionResponse.class, tags = {"Block",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = BlockTransactionResponse.class),
      @ApiResponse(code = 200, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/block/transaction",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<BlockTransactionResponse> blockTransaction(@ApiParam(value = "", required = true) @Valid @RequestBody BlockTransactionRequest blockTransactionRequest) {
    AtomicInteger statusCode = new AtomicInteger(200);

    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          BlockTransactionResponse blockTransactionResponse = new BlockTransactionResponse();
          String returnString = "";
          ObjectMapper mapper = new ObjectMapper();
          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          org.tron.model.Error error = new org.tron.model.Error();

          try {
            long blockIndex = blockTransactionRequest.getBlockIdentifier().getIndex();
            String txID = blockTransactionRequest.getTransactionIdentifier().getHash();
            BlockCapsule tronBlock = chainBaseManager.getBlockByNum(blockIndex);
            System.out.println("blockIndex:" + blockIndex);

            if (blockIndex > chainBaseManager.getDynamicPropertiesStore().getLatestSolidifiedBlockNum()) {
              throw new ItemNotFoundException();
            }

            BlockBalanceTraceCapsule blockBalanceTraceCapsule;
            if (blockIndex == 0) {
              blockBalanceTraceCapsule = genesisBlockBalanceTrace;
            } else {
              blockBalanceTraceCapsule = chainBaseManager.getBalanceTraceStore().getBlockBalanceTrace(tronBlock.getBlockId());
            }
            if (null == blockBalanceTraceCapsule) {
              throw new ItemNotFoundException("block balance info not find");
            }
            BalanceContract.BlockBalanceTrace blockBalanceTrace =
                blockBalanceTraceCapsule.getInstance();

            List<BalanceContract.TransactionBalanceTrace> tronTxs = blockBalanceTrace.getTransactionBalanceTraceList();
            for (BalanceContract.TransactionBalanceTrace tronTx : tronTxs) {
              if (ByteArray.toHexString(tronTx.getTransactionIdentifier().toByteArray()).equals(txID)) {
                //1. set tx
                org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
                    .transactionIdentifier(new org.tron.model.TransactionIdentifier()
                        .hash(ByteArray.toHexString(tronTx.getTransactionIdentifier().toByteArray())));
                //2. set operations
                List<BalanceContract.TransactionBalanceTrace.Operation> operations = tronTx.getOperationList();
                for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
                  rstTx.addOperationsItem(new org.tron.model.Operation()
                      .operationIdentifier(new OperationIdentifier().index(op.getOperationIdentifier()))
                      .type(tronTx.getType())
                      .status(tronTx.getStatus())
                      .amount(new Amount().currency(Default.CURRENCY).value(op.getAmount()))
                      .account(new AccountIdentifier().address(encode58Check(op.getAddress().toByteArray()))));
                }

                blockTransactionResponse.setTransaction(rstTx);
              }
            }

            returnString = mapper.writeValueAsString(blockTransactionResponse);
          } catch (java.lang.Error | ItemNotFoundException | BadItemException | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(500);
            error.setCode(100);
            error.setMessage("error:" + e.getMessage());
            error.setRetriable(false);
            returnString = JSON.toJSONString(error);
          }
          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));
  }
}
