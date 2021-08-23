package org.tron.api;

import com.google.common.primitives.Longs;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;

import java.lang.Error;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
import org.apache.commons.lang3.tuple.Pair;
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
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.Commons;
import org.tron.config.Constant;
import org.tron.core.ChainBaseManager;
import org.tron.core.Wallet;
import org.tron.core.capsule.BlockBalanceTraceCapsule;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.capsule.ExchangeCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.exception.BadItemException;
import org.tron.core.exception.ItemNotFoundException;
import org.tron.core.store.AccountTraceStore;
import org.tron.model.*;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.BalanceContract.TransactionBalanceTrace;
import org.tron.protos.contract.ExchangeContract;

import static org.tron.common.utils.StringUtil.encode58Check;
import static org.tron.core.config.Parameter.ChainSymbol.TRX_SYMBOL_BYTES;
import static org.tron.protos.Protocol.Transaction.Contract.ContractType;
import static org.tron.protos.Protocol.Transaction.Result.contractResult.SUCCESS;

@Controller
@RequestMapping("${openapi.rosetta.base-path:}")
public class BlockApiController implements BlockApi {
  @Autowired
  private ChainBaseManager chainBaseManager;

  @Autowired
  private AccountTraceStore accountTraceStore;

  @Autowired
  private Wallet wallet;

  private final NativeWebRequest request;

  private void initGenesis() {
    BlockCapsule genesis = chainBaseManager.getGenesisBlock();
    BlockBalanceTraceCapsule genesisBlockBalanceTraceCapsule = new BlockBalanceTraceCapsule(genesis);
    List<TransactionCapsule> transactionCapsules = genesis.getTransactions();
    for (TransactionCapsule transactionCapsule : transactionCapsules) {
      BalanceContract.TransferContract transferContract = getTransferContract(transactionCapsule);
      TransactionBalanceTrace.Operation operation = TransactionBalanceTrace.Operation.newBuilder()
          .setOperationIdentifier(0)
          .setAddress(transferContract.getToAddress())
          .setAmount(transferContract.getAmount())
          .build();

      TransactionBalanceTrace transactionBalanceTrace =
          TransactionBalanceTrace.newBuilder()
              .setTransactionIdentifier(transactionCapsule.getTransactionId().getByteString())
              .setType(ContractType.TransferContract.name())
              .setStatus(SUCCESS.name())
              .addOperation(operation)
              .build();
      genesisBlockBalanceTraceCapsule.addTransactionBalanceTrace(transactionBalanceTrace);

      accountTraceStore.recordBalanceWithBlock(
          transferContract.getToAddress().toByteArray(), 0, transferContract.getAmount());
    }

    chainBaseManager.getBalanceTraceStore().put(Longs.toByteArray(0), genesisBlockBalanceTraceCapsule);
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
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
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

//            if (blockIndex != null &&
//                blockIndex > chainBaseManager.getDynamicPropertiesStore().getLatestSolidifiedBlockNum()) {
//              throw new ItemNotFoundException("request block index > solidified index");
//            }

            //1. get block
            if (null != blockIndex) {
              if (blockIndex > chainBaseManager.getDynamicPropertiesStore().getLatestBlockHeaderNumber()) {
                error = Constant.newError(Constant.BLOCK_ID_OVER_CURRENT_LAST);
                returnString = JSON.toJSONString(error);
                ApiUtil.setExampleResponse(request, "application/json", returnString);
                statusCode.set(500);
                break;
              }
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
            List<TransactionCapsule> transactions = tronBlock.getTransactions();
            int index = 0;
            for (BalanceContract.TransactionBalanceTrace tronTx : tronTxs) {
              for (; index < transactions.size(); ++index) {
                if (transactions.get(index).getTransactionId().getByteString().equals(tronTx.getTransactionIdentifier())) {
                  break;
                }
              }
              if (index == transactions.size()) {
                throw new ItemNotFoundException("block transaction info not find");
              }
              rstTxs.add(toRosettaTx(tronTx, transactions.get(index), tronBlock.getNum()==0));
            }
            rstBlock.setTransactions(rstTxs);

            //4. response
            blockResponse.setBlock(rstBlock);

            returnString = mapper.writeValueAsString(blockResponse);
          } catch (ItemNotFoundException | BadItemException e) {
            statusCode.set(500);
            error = Constant.newError(Constant.BLOCK_IS_NOT_EXISTS);
            returnString = JSON.toJSONString(error);
          } catch (java.lang.Error | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(500);
            error = Constant.newError(Constant.INTERNAL_SERVER_ERROR);
            error.details(JSON.parseObject("{\"error_msg\":\"" + e.getMessage() + "\"}"));
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
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/block/transaction",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<BlockTransactionResponse> blockTransaction(
      @ApiParam(value = "", required = true)
      @Valid
      @RequestBody BlockTransactionRequest blockTransactionRequest) {
    int statusCode = 200;
    if(getRequest().isPresent()) {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          Pair<Integer, String> pair = handlerBlockTransaction(blockTransactionRequest);
          statusCode = pair.getLeft();
          ApiUtil.setExampleResponse(request, "application/json", pair.getRight());
          break;
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode));
  }

  public Pair<Integer, String> handlerBlockTransaction(BlockTransactionRequest blockTransactionRequest){
    String returnString;
    String txID = "";
    BlockTransactionResponse blockTransactionResponse = new BlockTransactionResponse();
    org.tron.model.Error error = new org.tron.model.Error();
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    try {
      long blockIndex = blockTransactionRequest.getBlockIdentifier().getIndex();
      txID = blockTransactionRequest.getTransactionIdentifier().getHash();
      System.out.println("blockIndex:" + blockIndex);

      if (blockIndex > chainBaseManager.getDynamicPropertiesStore().getLatestBlockHeaderNumber()) {
        error = Constant.newError(Constant.BLOCK_ID_OVER_CURRENT_LAST);
        returnString = JSON.toJSONString(error);
        return Pair.of(400, returnString);
      }

      //1. get blockBalanceTrace
      BlockCapsule tronBlock = chainBaseManager.getBlockByNum(blockIndex);
      BlockBalanceTraceCapsule blockBalanceTraceCapsule = chainBaseManager.getBalanceTraceStore().getBlockBalanceTrace(tronBlock.getBlockId());

      if (null == blockBalanceTraceCapsule) {
        throw new ItemNotFoundException("block balance info not find");
      }
      BalanceContract.BlockBalanceTrace blockBalanceTrace =
          blockBalanceTraceCapsule.getInstance();

      //2. get tx info when match txID
      List<BalanceContract.TransactionBalanceTrace> tronTxs = blockBalanceTrace.getTransactionBalanceTraceList();
      List<TransactionCapsule> transactions = tronBlock.getTransactions();
      int index = 0;
      for (BalanceContract.TransactionBalanceTrace tronTx : tronTxs) {
        if (ByteArray.toHexString(tronTx.getTransactionIdentifier().toByteArray()).equals(txID)) {
          for (; index < transactions.size(); ++index) {
            if (transactions.get(index).getTransactionId().getByteString().equals(tronTx.getTransactionIdentifier())) {
              break;
            }
          }
          if (index == transactions.size()) {
            throw new ItemNotFoundException("block transaction info not find");
          }
          blockTransactionResponse.setTransaction(toRosettaTx(tronTx, transactions.get(index), tronBlock.getNum() == 0));
          break;
        }
      }

      returnString = mapper.writeValueAsString(blockTransactionResponse);
    } catch ( ItemNotFoundException | BadItemException e) {
      error = Constant.newError(Constant.BLOCK_IS_NOT_EXISTS);
      error.setDetails(JSON.parseObject("{\"txID\":\"" + txID + "\"}"));
      returnString = JSON.toJSONString(error);
      return Pair.of(500, returnString);
    } catch ( java.lang.Error | JsonProcessingException e) {
      e.printStackTrace();
      error = Constant.newError(Constant.SERVER_EXCEPTION_CATCH);
      error.setDetails(JSON.parseObject("{\"txID\":\"" + txID + "\"}"));
      returnString = JSON.toJSONString(error);
      return Pair.of(500, returnString);
    }

    return Pair.of(200, returnString);
  }


  private List<TransactionBalanceTrace.Operation> removeUselessOperations(List<TransactionBalanceTrace.Operation> operations){
    List<TransactionBalanceTrace.Operation> result = new LinkedList<>();
    for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
      if (Arrays.equals(op.getAddress().toByteArray(),chainBaseManager.getAccountStore().getBlackholeAddress())) {
        continue;
      }
      if (op.getAmount()==0) {
        continue;
      }
      result.add(op);
    }
    return result;
  }

  private org.tron.model.Transaction toRosettaTx(BalanceContract.TransactionBalanceTrace transactionBalanceTrace, TransactionCapsule transaction, boolean isGenesisBlock){
    if (isGenesisBlock) {
      return toRosettaGenesisTx(transactionBalanceTrace);
    }
    if (transactionBalanceTrace.getType().equals(ContractType.TransferContract.name())) {
      return toRosettaTransferTx(transactionBalanceTrace,transaction);
    }
    if (transactionBalanceTrace.getType().equals(ContractType.CreateSmartContract.name()) ||
        transactionBalanceTrace.getType().equals(ContractType.TriggerSmartContract.name())) {
      return toRosettaVmTx(transactionBalanceTrace,transaction);
    }
    return toRosettaOtherTx(transactionBalanceTrace,transaction);
  }


  private org.tron.model.Transaction toRosettaGenesisTx(BalanceContract.TransactionBalanceTrace transactionBalanceTrace){
    //1. set tx
    org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
        .transactionIdentifier(new org.tron.model.TransactionIdentifier()
            .hash(ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray())));
    //2. set operations
    List<BalanceContract.TransactionBalanceTrace.Operation> operations = transactionBalanceTrace.getOperationList();
    for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
      long index = op.getOperationIdentifier();
      org.tron.model.Operation operation = new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index(index))
          .type("GenesisTransferContract")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(op.getAmount())))
          .account(new AccountIdentifier().address(encode58Check(op.getAddress().toByteArray())));
      rstTx.addOperationsItem(operation);
    }
    return rstTx;
  }

  private org.tron.model.Transaction toRosettaTransferTx(BalanceContract.TransactionBalanceTrace transactionBalanceTrace,TransactionCapsule transaction){
    Protocol.TransactionInfo reply = wallet.getTransactionInfoById(transactionBalanceTrace.getTransactionIdentifier());
    long fee = 0;
    if (reply != null) {
      fee = -reply.getFee();
    }
    //1. set tx
    org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
        .transactionIdentifier(new org.tron.model.TransactionIdentifier()
            .hash(ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray())));

    //2. set operations
    BalanceContract.TransferContract transferContract = transaction.getTransferContract();
    if (transferContract!=null) {
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index(0L))
          .type("TransferContract")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(-transferContract.getAmount())))
          .account(new AccountIdentifier().address(encode58Check(transferContract.getOwnerAddress().toByteArray()))));
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index(1L))
          .addRelatedOperationsItem(new OperationIdentifier().index(0L))
          .type("TransferContract")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(transferContract.getAmount())))
          .account(new AccountIdentifier().address(encode58Check(transferContract.getToAddress().toByteArray()))));
    }
    if (fee != 0) {
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index((long)(rstTx.getOperations().size())))
          .type("Fee")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(fee)))
          .account(new AccountIdentifier().address(encode58Check(transferContract.getOwnerAddress().toByteArray()))));
    }
    return rstTx;
  }

  private org.tron.model.Transaction toRosettaVmTx(BalanceContract.TransactionBalanceTrace transactionBalanceTrace,
                                                   TransactionCapsule transaction){
    long energyFee = 0;
    long netFee = 0;
    long multiSignFee = 0;
    long fee = 0;
    String txid = ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray());
    Protocol.TransactionInfo reply = wallet.getTransactionInfoById(transactionBalanceTrace.getTransactionIdentifier());
    if (reply != null) {
      energyFee = reply.getReceipt().getEnergyFee();
      netFee = reply.getReceipt().getNetFee();
      multiSignFee = reply.getFee()- energyFee - netFee - transaction.getInstance().getRet(0).getFee();
    }
    //1. set tx
    org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
        .transactionIdentifier(new org.tron.model.TransactionIdentifier()
            .hash(ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray())));
    //2. set operations
    List<BalanceContract.TransactionBalanceTrace.Operation> operations = removeUselessOperations(transactionBalanceTrace.getOperationList());

    String feeAddress = "";
    for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
      if (energyFee != 0 && op.getAmount() == -1 * energyFee) {
        feeAddress = encode58Check(op.getAddress().toByteArray());
        fee += op.getAmount();
        energyFee = 0;
        continue;
      }
      if (netFee != 0 && op.getAmount() == -1 * netFee) {
        feeAddress = encode58Check(op.getAddress().toByteArray());
        fee += op.getAmount();
        netFee = 0;
        continue;
      }
      if (multiSignFee != 0 && op.getAmount() == -1 * multiSignFee) {
        feeAddress = encode58Check(op.getAddress().toByteArray());
        fee += op.getAmount();
        multiSignFee = 0;
        continue;
      }
      long curIndex = rstTx.getOperations().size();
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index(curIndex))
          .type(transactionBalanceTrace.getType())
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(op.getAmount())))
          .account(new AccountIdentifier().address(encode58Check(op.getAddress().toByteArray()))));
    }
    if (fee != 0) {
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index((long)(rstTx.getOperations().size())))
          .type("Fee")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(fee)))
          .account(new AccountIdentifier().address(feeAddress)));
    }
    return rstTx;
  }

  private org.tron.model.Transaction toRosettaOtherTx(BalanceContract.TransactionBalanceTrace transactionBalanceTrace, TransactionCapsule transaction){
    //1. set tx
    org.tron.model.Transaction rstTx = new org.tron.model.Transaction()
        .transactionIdentifier(new org.tron.model.TransactionIdentifier()
            .hash(ByteArray.toHexString(transactionBalanceTrace.getTransactionIdentifier().toByteArray())));

    List<BalanceContract.TransactionBalanceTrace.Operation> operations = removeUselessOperations(transactionBalanceTrace.getOperationList());
    int transactionCount = getTransOperationCount(transaction.getInstance().getRawData().getContract(0));
    int feeOperationCount = operations.size() - transactionCount;
    if (transactionBalanceTrace.getType().equals(ContractType.MarketSellAssetContract.name()) ||
        transactionBalanceTrace.getType().equals(ContractType.MarketCancelOrderContract.name())) {
      feeOperationCount = 0;
    }
    long fee = 0;
    String feeAddress = "";
    for (BalanceContract.TransactionBalanceTrace.Operation op : operations) {
      long index = op.getOperationIdentifier();
      if (index < feeOperationCount) {
        fee += op.getAmount();
        if ("".equals(feeAddress)) {
          feeAddress = encode58Check(op.getAddress().toByteArray());
        }
        continue;
      }
      index = rstTx.getOperations().size();
      org.tron.model.Operation operation = new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index(index))
          .type(transactionBalanceTrace.getType())
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(op.getAmount())))
          .account(new AccountIdentifier().address(encode58Check(op.getAddress().toByteArray())));
      rstTx.addOperationsItem(operation);
    }
    if (fee != 0) {
      rstTx.addOperationsItem(new org.tron.model.Operation()
          .operationIdentifier(new OperationIdentifier().index((long)(rstTx.getOperations().size())))
          .type("Fee")
          .status(transactionBalanceTrace.getStatus())
          .amount(new Amount().currency(Default.CURRENCY).value(Long.toString(fee)))
          .account(new AccountIdentifier().address(feeAddress)));
    }
    return rstTx;
  }

  private int getTransOperationCount(Protocol.Transaction.Contract contract) {
    Any contractParameter = contract.getParameter();
    try {
      if (contract.getType().equals(ContractType.ExchangeCreateContract)) {
        final ExchangeContract.ExchangeCreateContract exchangeCreateContract =
            contractParameter.unpack(ExchangeContract.ExchangeCreateContract.class);
        if (Arrays.equals(exchangeCreateContract.getFirstTokenId().toByteArray(), TRX_SYMBOL_BYTES) ||
            Arrays.equals(exchangeCreateContract.getSecondTokenId().toByteArray(), TRX_SYMBOL_BYTES)) {
          return 1;
        }
      } else if (contract.getType().equals(ContractType.ExchangeInjectContract)) {
        final ExchangeContract.ExchangeInjectContract exchangeInjectContract =
            contractParameter.unpack(ExchangeContract.ExchangeInjectContract.class);

        ExchangeCapsule exchangeCapsule = Commons.getExchangeStoreFinal(chainBaseManager.getDynamicPropertiesStore(),
            chainBaseManager.getExchangeStore(), chainBaseManager.getExchangeV2Store()).
            get(ByteArray.fromLong(exchangeInjectContract.getExchangeId()));
        if (Arrays.equals(exchangeCapsule.getFirstTokenId(), TRX_SYMBOL_BYTES) ||
            Arrays.equals(exchangeCapsule.getSecondTokenId(), TRX_SYMBOL_BYTES)) {
          return 1;
        }
      } else if (contract.getType().equals(ContractType.ExchangeTransactionContract)) {
        final ExchangeContract.ExchangeTransactionContract exchangeTransactionContract =
            contractParameter.unpack(ExchangeContract.ExchangeTransactionContract.class);

        ExchangeCapsule exchangeCapsule = Commons.getExchangeStoreFinal(chainBaseManager.getDynamicPropertiesStore(),
                chainBaseManager.getExchangeStore(), chainBaseManager.getExchangeV2Store()).
                get(ByteArray.fromLong(exchangeTransactionContract.getExchangeId()));
        if (Arrays.equals(exchangeCapsule.getFirstTokenId(), TRX_SYMBOL_BYTES) ||
            Arrays.equals(exchangeCapsule.getSecondTokenId(), TRX_SYMBOL_BYTES)) {
          return 1;
        }
      }
      else if (contract.getType().equals(ContractType.ExchangeWithdrawContract)) {
        final ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract =
            contractParameter.unpack(ExchangeContract.ExchangeWithdrawContract.class);

        ExchangeCapsule exchangeCapsule = Commons.getExchangeStoreFinal(chainBaseManager.getDynamicPropertiesStore(),
            chainBaseManager.getExchangeStore(), chainBaseManager.getExchangeV2Store()).
            get(ByteArray.fromLong(exchangeWithdrawContract.getExchangeId()));
        if (Arrays.equals(exchangeCapsule.getFirstTokenId(), TRX_SYMBOL_BYTES) ||
            Arrays.equals(exchangeCapsule.getSecondTokenId(), TRX_SYMBOL_BYTES)) {
          return 1;
        }
      }
    } catch (ItemNotFoundException | InvalidProtocolBufferException exception) {
      return 0;
    }
    Map<ContractType,Integer>transactionCountMap = new HashMap<>();
    transactionCountMap.put(ContractType.FreezeBalanceContract,1);
    transactionCountMap.put(ContractType.UnfreezeBalanceContract,1);
    transactionCountMap.put(ContractType.WithdrawBalanceContract,1);
    transactionCountMap.put(ContractType.ParticipateAssetIssueContract, 2);
    return transactionCountMap.get(contract.getType())==null ?
        0 : transactionCountMap.get(contract.getType());
  }
}
