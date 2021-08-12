package org.tron.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.security.auth.login.AccountException;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.tron.common.BandwidthProcessor;
import org.tron.common.Default;
import org.tron.common.ErrorCodeUtil;
import org.tron.common.crypto.ECKey;
import org.tron.core.ChainBaseManager;
import org.tron.core.capsule.TransactionResultCapsule;
import org.tron.core.exception.ContractValidateException;
import org.tron.model.*;
import org.tron.model.Error;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.StringUtil;
import org.tron.common.utils.Commons;
import org.tron.config.Constant;
import org.tron.core.Wallet;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.config.args.Args;
import org.tron.core.exception.BadItemException;
import org.tron.core.store.DynamicPropertiesStore;
import org.tron.model.ConstructionCombineRequest;
import org.tron.model.ConstructionCombineResponse;
import org.tron.model.ConstructionDeriveRequest;
import org.tron.model.ConstructionDeriveResponse;
import org.tron.model.ConstructionHashRequest;
import org.tron.model.ConstructionMetadataRequest;
import org.tron.model.ConstructionMetadataResponse;
import org.tron.model.ConstructionPayloadsRequest;
import org.tron.model.ConstructionPayloadsResponse;
import org.tron.model.ConstructionPreprocessRequest;
import org.tron.model.ConstructionPreprocessResponse;
import org.tron.model.ConstructionSubmitRequest;
import org.tron.model.CurveType;
import org.tron.model.PublicKey;
import org.tron.model.Signature;
import static org.tron.common.utils.StringUtil.encode58Check;

@Controller
@RequestMapping("${openapi.rosetta.base-path:}")
public class ConstructionApiController implements ConstructionApi {

  private final NativeWebRequest request;

  @Autowired
  private DynamicPropertiesStore dynamicPropertiesStore;

  @Autowired
  private ChainBaseManager chainBaseManager;

  @Autowired
  private Wallet wallet;

  private ObjectMapper objectMapper = new ObjectMapper();


  @org.springframework.beans.factory.annotation.Autowired
  public ConstructionApiController(NativeWebRequest request) {
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }


  /**
   * POST /construction/parse : Parse a Transaction
   * Parse is called on both unsigned and signed transactions to understand the intent of the formulated transaction. This is run as a sanity check before signing (after &#x60;/construction/payloads&#x60;) and before broadcast (after &#x60;/construction/combine&#x60;).
   *
   * @param constructionParseRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Parse a Transaction", nickname = "constructionParse", notes = "Parse is called on both unsigned and signed transactions to understand the intent of the formulated transaction. This is run as a sanity check before signing (after `/construction/payloads`) and before broadcast (after `/construction/combine`). ", response = ConstructionParseResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionParseResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/parse",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<ConstructionParseResponse> constructionParse(@ApiParam(value = "", required = true) @Valid @RequestBody ConstructionParseRequest constructionParseRequest) {
    AtomicInteger statusCode = new AtomicInteger(200);

    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String returnString = "";
          ObjectMapper mapper = new ObjectMapper();
          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          Error error = new Error();

          try {
            ConstructionParseResponse constructionParseResponse = new org.tron.model.ConstructionParseResponse();

            Boolean signed = constructionParseRequest.getSigned();
            TransactionCapsule transaction = new TransactionCapsule(
                ByteArray.fromHexString(constructionParseRequest.getTransaction()));

//            String status = Protocol.Transaction.Result.contractResult.DEFAULT.name();
            long fee = 0;
            if (transaction.getInstance().getRetCount() > 0) {
              fee = transaction.getInstance().getRet(0).getFee();
            }

            java.util.List<org.tron.protos.Protocol.Transaction.Contract> contracts =
                transaction.getInstance().getRawData().getContractList();

            long op_index = 0;
            for (org.tron.protos.Protocol.Transaction.Contract contract : contracts) {
              if (org.tron.protos.Protocol.Transaction.Contract.ContractType.TransferContract != contract.getType()) {
                continue;
              }
              BalanceContract.TransferContract balanceContract =
                  contract.getParameter().unpack(BalanceContract.TransferContract.class);

              //1. get signers
              if (signed) {
                String from = encode58Check(balanceContract.getOwnerAddress().toByteArray());
                constructionParseResponse.addSignersItem(from);
              }

              //2. get ops
              String own_amount = balanceContract.getAmount() == 0 ? "" : "-";
              constructionParseResponse.addOperationsItem(new org.tron.model.Operation()
                  .operationIdentifier(new OperationIdentifier().index(op_index))
                  .account(new AccountIdentifier().address(encode58Check(balanceContract.getOwnerAddress().toByteArray())))
                  .amount(new Amount().value(own_amount+balanceContract.getAmount()).currency(Default.CURRENCY))
                  .type(contract.getType().toString()));

              constructionParseResponse.addOperationsItem(new org.tron.model.Operation()
                  .operationIdentifier(new OperationIdentifier().index(op_index+1))
                  .addRelatedOperationsItem(new OperationIdentifier().index(op_index))
                  .account(new AccountIdentifier().address(encode58Check(balanceContract.getToAddress().toByteArray())))
                  .amount(new Amount().value(Long.toString(balanceContract.getAmount())).currency(Default.CURRENCY))
                  .type(contract.getType().toString()));

              op_index += 2;
              if (fee > 0) {
                constructionParseResponse.addOperationsItem(new org.tron.model.Operation()
                    .operationIdentifier(new OperationIdentifier().index(op_index))
                    .account(new AccountIdentifier().address(encode58Check(balanceContract.getOwnerAddress().toByteArray())))
                    .amount(new Amount().value(Long.toString(fee * -1)).currency(Default.CURRENCY))
                    .type("Fee"));
                op_index += 1;
              }
            }

            //3. get metadata
            Map<String, Object> metadatas = new HashMap<>();
            metadatas.put("reference_block_num", transaction.getInstance().getRawData().getRefBlockNum());
            metadatas.put("reference_block_hash", ByteArray.toHexString(transaction.getInstance().getRawData().getRefBlockHash().toByteArray()));
            metadatas.put("expiration", transaction.getExpiration());
            metadatas.put("timestamp", transaction.getTimestamp());
            if (fee > 0) {
              metadatas.put("fee", fee);
            }
            JSONObject metadata = new JSONObject(metadatas);
            constructionParseResponse.setMetadata(metadata);

            returnString = mapper.writeValueAsString(constructionParseResponse);
          } catch (java.lang.Error | InvalidProtocolBufferException | JsonProcessingException | BadItemException e) {
            e.printStackTrace();
            statusCode.set(500);
            error = Constant.newError(Constant.INVALID_TRANSACTION_FORMAT);
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
   * POST /construction/derive : Derive an Address from a PublicKey
   * Derive returns the network-specific address associated with a public key. Blockchains that require an on-chain action to create an account should not implement this method.
   *
   * @param constructionDeriveRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Derive an Address from a PublicKey", nickname = "constructionDerive", notes = "Derive returns the network-specific address associated with a public key. Blockchains that require an on-chain action to create an account should not implement this method.", response = ConstructionDeriveResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionDeriveResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/derive",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<ConstructionDeriveResponse> constructionDerive(
      @ApiParam(value = "", required = true)
      @Valid
      @RequestBody
          ConstructionDeriveRequest constructionDeriveRequest) {

    if (getRequest().isPresent()) {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          PublicKey publicKey = constructionDeriveRequest.getPublicKey();
          if (CurveType.SECP256K1.equals(publicKey.getCurveType())) {
            String hexBytes = publicKey.getHexBytes();
            ECKey ecKey = new ECKey(ByteArray.fromHexString(hexBytes), false);
            byte[] address = ecKey.getAddress();
            ConstructionDeriveResponse response = new ConstructionDeriveResponse();
            response.address(StringUtil.encode58Check(address));
            return new ResponseEntity<>(response, HttpStatus.OK);
          }
          break;
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.valueOf(200));
  }

  /**
   * POST /construction/preprocess : Create a Request to Fetch Metadata
   * Preprocess is called prior to &#x60;/construction/payloads&#x60; to construct a request for any metadata that is needed for transaction construction given (i.e. account nonce). The request returned from this method will be used by the caller (in a different execution environment) to call the &#x60;/construction/metadata&#x60; endpoint.
   *
   * @param constructionPreprocessRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Create a Request to Fetch Metadata", nickname = "constructionPreprocess", notes = "Preprocess is called prior to `/construction/payloads` to construct a request for any metadata that is needed for transaction construction given (i.e. account nonce). The request returned from this method will be used by the caller (in a different execution environment) to call the `/construction/metadata` endpoint.", response = ConstructionPreprocessResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionPreprocessResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/preprocess",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<ConstructionPreprocessResponse> constructionPreprocess(@ApiParam(value = "", required = true) @Valid @RequestBody ConstructionPreprocessRequest constructionPreprocessRequest) {
    AtomicInteger statusCode = new AtomicInteger(HttpStatus.OK.value());
    String returnString = "";
    if (getRequest().isPresent()) {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          validatePreprocessRequest(constructionPreprocessRequest);
          try {
            Pair<String, TransactionCapsule> pair = buildTransaction(constructionPreprocessRequest.getOperations(), null);
            TransactionCapsule transaction = pair.getRight();
            ConstructionPreprocessResponse response = new ConstructionPreprocessResponse();
            Map<String, Object> options = new HashMap<>();
            options.put("unsigned_transaction", ByteArray.toHexString(transaction.getInstance().toByteArray()));
            response.options(options);
            return new ResponseEntity<>(response, HttpStatus.OK);
          } catch (AccountException e) {
            Error error = Constant.newError(Constant.ACCOUNT_IS_NOT_EXISTS)
                .retriable(false)
                .details(JSON.parseObject("{\"message\":\"Invalid Address\"}"));
            try {
              returnString = objectMapper.writeValueAsString(error);
            } catch (JsonProcessingException ex) {
              //ex.printStackTrace();
            }
          }
          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));
  }

  public void validatePreprocessRequest(ConstructionPreprocessRequest constructionPreprocessRequest) {

  }


  /**
   * POST /construction/combine : Create Network Transaction from Signatures
   * Combine creates a network-specific transaction from an unsigned transaction and
   * an array of provided signatures. The signed transaction returned from this method
   * will be sent to the &#x60;/construction/submit&#x60; endpoint by the caller.
   *
   * @param constructionCombineRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Create Network Transaction from Signatures", nickname = "constructionCombine", notes = "Combine creates a network-specific transaction from an unsigned transaction and an array of provided signatures. The signed transaction returned from this method will be sent to the `/construction/submit` endpoint by the caller.", response = ConstructionCombineResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionCombineResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/combine",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<ConstructionCombineResponse> constructionCombine(@ApiParam(value = "", required = true) @Valid @RequestBody ConstructionCombineRequest constructionCombineRequest) {
    AtomicInteger statusCode = new AtomicInteger(HttpStatus.OK.value());
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String returnString = "";
          //BalanceContract.TransferContract transferContract = BalanceContract.TransferContract.newBuilder().setAmount(10)
          //        .setOwnerAddress(ByteString.copyFrom(ByteArray.fromHexString("121212a9cf")))
          //        .setToAddress(ByteString.copyFrom(ByteArray.fromHexString("232323a9cf"))).build();
          //Protocol.Transaction transactionTest = Protocol.Transaction.newBuilder().setRawData(
          //        Protocol.Transaction.raw.newBuilder().setTimestamp(DateTime.now().minusDays(4).getMillis())
          //                .setRefBlockNum(1)
          //                .addContract(
          //                        Protocol.Transaction.Contract.newBuilder().setType(Protocol.Transaction.Contract.ContractType.TransferContract)
          //                                .setParameter(Any.pack(transferContract)).build()).build())
          //        .build();
          //System.out.println(ByteArray.toHexString(transactionTest.getRawData().toByteArray()));
          //String priKey = "FC8BF0238748587B9617EB6D15D47A66C0E07C1A1959033CF249C6532DC29FE6";
          //TransactionCapsule transactionCapsule = new TransactionCapsule(transactionTest);
          //transactionCapsule.sign(ByteArray.fromHexString(priKey));
          //System.out.println(ByteArray.toHexString(transactionCapsule.getInstance().getSignature(0).toByteArray()));
          //constructionCombineRequest.setUnsignedTransaction(ByteArray.toHexString(transactionTest.toByteArray()));
          try {
            TransactionCapsule transaction = new TransactionCapsule(
                    ByteArray.fromHexString(constructionCombineRequest.getUnsignedTransaction()));
            Protocol.Transaction.Builder transactionBuilder = transaction.getInstance().toBuilder();
            for (Signature signature : constructionCombineRequest.getSignatures()) {
              transactionBuilder.addSignature(ByteString.copyFrom(ByteArray.fromHexString(signature.getHexBytes())));
            }
            ConstructionCombineResponse constructionCombineResponse = new ConstructionCombineResponse();
            constructionCombineResponse.setSignedTransaction(ByteArray.toHexString(transactionBuilder.build().toByteArray()));
            returnString = objectMapper.writeValueAsString(constructionCombineResponse);
          } catch (BadItemException | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Error error = Constant.newError(Constant.INVALID_TRANSACTION_FORMAT);
            try {
              returnString = objectMapper.writeValueAsString(error);
            } catch (JsonProcessingException ex) {
              //ex.printStackTrace();
            }
          }

          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));
  }

    /**
     * POST /construction/metadata : Get Metadata for Transaction Construction
     * Get any information required to construct a transaction for a specific network. Metadata returned here could be a recent hash to use, an account sequence number, or even arbitrary chain state. The request used when calling this endpoint is often created by calling &#x60;/construction/preprocess&#x60; in an offline environment. It is important to clarify that this endpoint should not pre-construct any transactions for the client (this should happen in &#x60;/construction/payloads&#x60;). This endpoint is left purposely unstructured because of the wide scope of metadata that could be required.
     *
     * @param constructionMetadataRequest  (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get Metadata for Transaction Construction", nickname = "constructionMetadata", notes = "Get any information required to construct a transaction for a specific network. Metadata returned here could be a recent hash to use, an account sequence number, or even arbitrary chain state. The request used when calling this endpoint is often created by calling `/construction/preprocess` in an offline environment. It is important to clarify that this endpoint should not pre-construct any transactions for the client (this should happen in `/construction/payloads`). This endpoint is left purposely unstructured because of the wide scope of metadata that could be required.", response = ConstructionMetadataResponse.class, tags = {"Construction",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionMetadataResponse.class),
        @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
    @RequestMapping(value = "/construction/metadata",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    public ResponseEntity<ConstructionMetadataResponse> constructionMetadata
    (@ApiParam(value = "", required = true) @Valid @RequestBody ConstructionMetadataRequest constructionMetadataRequest)
    {
      HttpStatus statusCode = HttpStatus.OK;
      String returnString = "";
      if (getRequest().isPresent()) {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
          if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
            BlockCapsule.BlockId blockId = new BlockCapsule.BlockId(dynamicPropertiesStore.getLatestBlockHeaderHash());
            long referenceBlockNum = blockId.getNum();
            String referenceBlockHash = blockId.toString();
            long expiration = dynamicPropertiesStore.getLatestBlockHeaderTimestamp() + Args.getInstance()
                .getTrxExpirationTimeInMilliseconds();
            long timestamp = System.currentTimeMillis();

            Map<String, Object> metadatas = new HashMap<>();
            metadatas.put("reference_block_num", referenceBlockNum);
            metadatas.put("reference_block_hash", referenceBlockHash);
            metadatas.put("expiration", expiration);
            metadatas.put("timestamp", timestamp);
            ConstructionMetadataResponse response = new ConstructionMetadataResponse();
            try {
              if (constructionMetadataRequest.getOptions() != null) {
                JSONObject metadata = new JSONObject((Map<String, Object>) constructionMetadataRequest.getOptions());
                TransactionCapsule transaction = new TransactionCapsule(
                    ByteArray.fromHexString(metadata.getString("unsigned_transaction")));
                BandwidthProcessor processor = new BandwidthProcessor(chainBaseManager);
                transaction.setReference(referenceBlockNum,ByteArray.fromHexString(referenceBlockHash));
                transaction.setExpiration(expiration);
                transaction.setTimestamp(timestamp);
                transaction.sign(ByteArray.fromHexString("111111"));
                transaction.setTimestamp();
                long fee = processor.calculateConsume(transaction);
                if (fee != 0) {
                  metadatas.put("fee", fee);
                }
                response.addSuggestedFeeItem(new Amount().value(Long.toString(fee)).currency(Default.CURRENCY));
                response.setMetadata(metadatas);
                returnString = objectMapper.writeValueAsString(response);
              }
            } catch (BadItemException | ContractValidateException | JsonProcessingException e) {
              e.printStackTrace();
              statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
              Error error = Constant.newError(Constant.INVALID_REQUEST_FORMAT);
              returnString = JSON.toJSONString(error);
            }
            ApiUtil.setExampleResponse(request, "application/json", returnString);
          }
        }
      }

      return new ResponseEntity<>(statusCode);
    }

  /**
   * POST /construction/hash : Get the Hash of a Signed Transaction
   * TransactionHash returns the network-specific transaction hash for a signed transaction.
   *
   * @param constructionHashRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Get the Hash of a Signed Transaction", nickname = "constructionHash", notes = "TransactionHash returns the network-specific transaction hash for a signed transaction.", response = TransactionIdentifierResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Expected response to a valid request", response = TransactionIdentifierResponse.class),
          @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/hash",
          produces = {"application/json"},
          consumes = {"application/json"},
          method = RequestMethod.POST)
  public ResponseEntity<TransactionIdentifierResponse> constructionHash(@ApiParam(value = "", required = true) @Valid @RequestBody ConstructionHashRequest constructionHashRequest) {
    AtomicInteger statusCode = new AtomicInteger(HttpStatus.OK.value());
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String returnString = "{ \"transaction_hash\" : \"transaction_hash\" }";
          try {
            TransactionCapsule transaction = new TransactionCapsule(
                    ByteArray.fromHexString(constructionHashRequest.getSignedTransaction()));
            String transactionHash = transaction.getTransactionId().toString();
            TransactionIdentifierResponse transactionIdentifierResponse = new TransactionIdentifierResponse();
            transactionIdentifierResponse.setTransactionIdentifier(new TransactionIdentifier().hash(transactionHash));
            returnString = objectMapper.writeValueAsString(transactionIdentifierResponse);
          } catch (BadItemException | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Error error = Constant.newError(Constant.INVALID_TRANSACTION_FORMAT);
            try {
              returnString = objectMapper.writeValueAsString(error);
            } catch (JsonProcessingException ex) {
              //ex.printStackTrace();
            }
          }

          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));

    }

  /**
   * POST /construction/submit : Submit a Signed Transaction
   * Submit a pre-signed transaction to the node.
   * This call should not block on the transaction being included in a block.
   * Rather, it should return immediately with an indication of whether or not
   * the transaction was included in the mempool. The transaction submission
   * response should only return a 200 status if the submitted transaction could
   * be included in the mempool. Otherwise, it should return an error.
   *
   * @param constructionSubmitRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Submit a Signed Transaction", nickname = "constructionSubmit", notes = "Submit a pre-signed transaction to the node. This call should not block on the transaction being included in a block. Rather, it should return immediately with an indication of whether or not the transaction was included in the mempool. The transaction submission response should only return a 200 status if the submitted transaction could be included in the mempool. Otherwise, it should return an error.", response = TransactionIdentifierResponse.class, tags = {"Construction",})
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Expected response to a valid request",
                  response = TransactionIdentifierResponse.class),
          @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/construction/submit",
          produces = {"application/json"},
          consumes = {"application/json"},
          method = RequestMethod.POST)
  public ResponseEntity<TransactionIdentifierResponse> constructionSubmit(
          @ApiParam(value = "", required = true) @Valid @RequestBody ConstructionSubmitRequest constructionSubmitRequest) {
    AtomicInteger statusCode = new AtomicInteger(HttpStatus.OK.value());

    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String returnString = "";
          String transactionHash = "";
          Error error = null;
          try {
            TransactionCapsule transactionSigned = new TransactionCapsule(
                    ByteArray.fromHexString(constructionSubmitRequest.getSignedTransaction()));
            transactionSigned.resetResult();
            GrpcAPI.Return result = wallet.broadcastTransaction(transactionSigned.getInstance());
            transactionHash = transactionSigned.getTransactionId().toString();
            if (result.getResult()) {
              TransactionIdentifierResponse transactionIdentifierResponse = new TransactionIdentifierResponse();
              TransactionIdentifier transactionIdentifier = new TransactionIdentifier();
              transactionIdentifier.setHash(transactionHash);
              transactionIdentifierResponse.setTransactionIdentifier(transactionIdentifier);
              returnString = objectMapper.writeValueAsString(transactionIdentifierResponse);
            } else if (ErrorCodeUtil.containsTronErrorCode(result.getCode().getNumber())) {
              statusCode.set(HttpStatus.INTERNAL_SERVER_ERROR.value());
              error = ErrorCodeUtil.getTronError(result.getCode().getNumber());
              error.details(JSON.parseObject("{\"txID\":\"" + transactionHash + "\"}")).message(result.getMessage().toStringUtf8());
              if (result.getCodeValue() == GrpcAPI.Return.response_code.DUP_TRANSACTION_ERROR.getNumber()) {
                error.message("Dup transaction");
              }
              returnString = objectMapper.writeValueAsString(error);
            } else {
              statusCode.set(HttpStatus.INTERNAL_SERVER_ERROR.value());
              error = Constant.newError(Constant.BROADCAST_TRANSACTION_FAILED);
              error.details(JSON.parseObject("{\"txID\":\"" + transactionHash + "\"}")).message(result.getMessage().toStringUtf8());
              if (result.getCodeValue() == GrpcAPI.Return.response_code.DUP_TRANSACTION_ERROR.getNumber()) {
                error.message("Dup transaction");
              }
              returnString = objectMapper.writeValueAsString(error);
            }
          } catch (BadItemException | JsonProcessingException e) {
            e.printStackTrace();
            statusCode.set(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error = Constant.newError(Constant.INVALID_TRANSACTION_FORMAT);
            error.details(JSON.parseObject("{\"txID\":\"" + transactionHash + "\"}"));
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
   * POST /construction/payloads : Generate an Unsigned Transaction and Signing Payloads
   * Payloads is called with an array of operations and the response from &#x60;/construction/metadata&#x60;. It returns an unsigned transaction blob and a collection of payloads that must be signed by particular addresses using a certain SignatureType. The array of operations provided in transaction construction often times can not specify all \&quot;effects\&quot; of a transaction (consider invoked transactions in Ethereum). However, they can deterministically specify the \&quot;intent\&quot; of the transaction, which is sufficient for construction. For this reason, parsing the corresponding transaction in the Data API (when it lands on chain) will contain a superset of whatever operations were provided during construction.
   *
   * @param constructionPayloadsRequest  (required)
   * @return Expected response to a valid request (status code 200)
   *         or unexpected error (status code 200)
   */
  @ApiOperation(value = "Generate an Unsigned Transaction and Signing Payloads", nickname = "constructionPayloads", notes = "Payloads is called with an array of operations and the response from `/construction/metadata`. It returns an unsigned transaction blob and a collection of payloads that must be signed by particular addresses using a certain SignatureType. The array of operations provided in transaction construction often times can not specify all \"effects\" of a transaction (consider invoked transactions in Ethereum). However, they can deterministically specify the \"intent\" of the transaction, which is sufficient for construction. For this reason, parsing the corresponding transaction in the Data API (when it lands on chain) will contain a superset of whatever operations were provided during construction.", response = ConstructionPayloadsResponse.class, tags={ "Construction", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = ConstructionPayloadsResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class) })
  @RequestMapping(value = "/construction/payloads",
      produces = { "application/json" },
      consumes = { "application/json" },
      method = RequestMethod.POST)
  public ResponseEntity<ConstructionPayloadsResponse> constructionPayloads(
      @ApiParam(value = "" ,required=true )
      @Valid @RequestBody
          ConstructionPayloadsRequest constructionPayloadsRequest) {
    AtomicInteger statusCode = new AtomicInteger(HttpStatus.OK.value());
    String returnString = "";
    if (getRequest().isPresent()) {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          validatePayloadsRequest(constructionPayloadsRequest);
          try {
            Pair<String, TransactionCapsule> pair = buildTransaction(constructionPayloadsRequest.getOperations(), constructionPayloadsRequest.getMetadata());
            TransactionCapsule transaction = pair.getRight();
            String owner = pair.getLeft();
            SigningPayload payloadItem = new SigningPayload();
            payloadItem.address(owner)
                .hexBytes(ByteArray.toHexString(transaction.getTransactionId().getBytes()))
                .signatureType(SignatureType.ECDSA_RECOVERY);
            ConstructionPayloadsResponse response = new ConstructionPayloadsResponse();
            response.unsignedTransaction(ByteArray.toHexString(transaction.getInstance().toByteArray()))
                .addPayloadsItem(payloadItem);
            return new ResponseEntity<>(response, HttpStatus.OK);
          } catch (AccountException e) {
            Error error = Constant.newError(Constant.ACCOUNT_IS_NOT_EXISTS)
                .retriable(false)
                .details(JSON.parseObject("{\"message\":\"Invalid Address\"}"));
            try {
              returnString = objectMapper.writeValueAsString(error);
            } catch (JsonProcessingException ex) {
              //ex.printStackTrace();
            }
          }
          ApiUtil.setExampleResponse(request, "application/json", returnString);
          break;
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.valueOf(statusCode.get()));

  }

  public void validatePayloadsRequest(ConstructionPayloadsRequest constructionPayloadsRequest) {

  }

  public Pair<String, TransactionCapsule> buildTransaction(List<Operation> operations, Object metaObj) throws AccountException {
    Operation from, to;
    if (StringUtils.isNotEmpty(operations.get(0).getAmount().getValue())
        && operations.get(0).getAmount().getValue().startsWith("-")) {
      from = operations.get(0);
      to = operations.get(1);
    } else {
      from = operations.get(1);
      to = operations.get(0);

    }
    BalanceContract.TransferContract.Builder builder = BalanceContract.TransferContract
        .newBuilder();
    String src = from.getAccount().getAddress();
    byte[] fromBase58Check = Commons.decodeFromBase58Check(src);
    String dest = to.getAccount().getAddress();
    byte[] toBase58Check = Commons.decodeFromBase58Check(dest);
    if (null == fromBase58Check || null == toBase58Check) {
      throw new AccountException();
    }

    ByteString bsOwner = ByteString.copyFrom(fromBase58Check);
    builder.setOwnerAddress(bsOwner);

    ByteString bsTo = ByteString.copyFrom(toBase58Check);
    builder.setToAddress(bsTo);

    builder.setAmount(Long.parseLong(to.getAmount().getValue()));
    BalanceContract.TransferContract contract = builder.build();
    TransactionCapsule transactionCapsule = new TransactionCapsule(contract,
        Protocol.Transaction.Contract.ContractType.TransferContract);

    if (metaObj != null) {
      JSONObject metadata = new JSONObject((Map<String, Object>) metaObj);
      String referenceBlockHash = metadata.getString("reference_block_hash");
      int referenceBlockNum = metadata.getIntValue("reference_block_num");
      transactionCapsule.setReference(referenceBlockNum, ByteArray.fromHexString(referenceBlockHash));
      long expiration = metadata.getLongValue("expiration");
      transactionCapsule.setExpiration(expiration);
      long timestamp = metadata.getLongValue("timestamp");
      transactionCapsule.setTimestamp(timestamp);
      long fee = metadata.getLongValue("fee");
      if (fee > 0) {
        TransactionResultCapsule ret = new TransactionResultCapsule();
        ret.setFee(fee);
        transactionCapsule.setResult(ret);
      }
    }

    return Pair.of(src, transactionCapsule);
  }

}

