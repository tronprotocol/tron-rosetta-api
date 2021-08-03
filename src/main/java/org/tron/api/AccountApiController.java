package org.tron.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Arrays;
import java.util.Optional;
import javax.security.auth.login.AccountException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.tron.common.ApiUtil;
import org.tron.common.Default;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.Commons;
import org.tron.common.utils.Sha256Hash;
import org.tron.config.Constant;
import org.tron.core.capsule.AccountCapsule;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.db.BlockIndexStore;
import org.tron.core.db.Manager;
import org.tron.core.db2.core.Chainbase;
import org.tron.core.exception.BadItemException;
import org.tron.core.exception.ItemNotFoundException;
import org.tron.core.store.AccountStore;
import org.tron.core.store.AccountTraceStore;
import org.tron.core.store.BalanceTraceStore;
import org.tron.core.store.DynamicPropertiesStore;
import org.tron.model.AccountBalanceRequest;
import org.tron.model.AccountBalanceResponse;
import org.tron.model.AccountIdentifier;
import org.tron.model.Amount;
import org.tron.model.BlockIdentifier;
import org.tron.model.Error;
import org.tron.model.PartialBlockIdentifier;

@Controller
@RequestMapping("${openapi.rosetta.base-path:}")
@Slf4j(topic = "db")
public class AccountApiController implements AccountApi {

  private final NativeWebRequest request;

  @Autowired
  private BalanceTraceStore balanceTraceStore;

  @Autowired
  private Manager manager;

  @Autowired
  private AccountStore accountStore;

  @Autowired
  private AccountTraceStore accountTraceStore;

  @Autowired
  private DynamicPropertiesStore dynamicPropertiesStore;

  @Autowired
  private BlockIndexStore blockIndexStore;

  @org.springframework.beans.factory.annotation.Autowired
  public AccountApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  /**
   * POST /account/balance : Get an Account Balance
   * Get an array of all AccountBalances for an AccountIdentifier and the BlockIdentifier at which the balance lookup was performed. The BlockIdentifier must always be returned because some consumers of account balance data need to know specifically at which block the balance was calculated to compare balances they compute from operations with the balance returned by the node. It is important to note that making a balance request for an account without populating the SubAccountIdentifier should not result in the balance of all possible SubAccountIdentifiers being returned. Rather, it should result in the balance pertaining to no SubAccountIdentifiers being returned (sometimes called the liquid balance). To get all balances associated with an account, it may be necessary to perform multiple balance requests with unique AccountIdentifiers. It is also possible to perform a historical balance lookup (if the server supports it) by passing in an optional BlockIdentifier.
   *
   * @param accountBalanceRequest (required)
   * @return Expected response to a valid request (status code 200)
   * or unexpected error (status code 200)
   */
  @ApiOperation(value = "Get an Account Balance", nickname = "accountBalance", notes = "Get an array of all AccountBalances for an AccountIdentifier and the BlockIdentifier at which the balance lookup was performed. The BlockIdentifier must always be returned because some consumers of account balance data need to know specifically at which block the balance was calculated to compare balances they compute from operations with the balance returned by the node. It is important to note that making a balance request for an account without populating the SubAccountIdentifier should not result in the balance of all possible SubAccountIdentifiers being returned. Rather, it should result in the balance pertaining to no SubAccountIdentifiers being returned (sometimes called the liquid balance). To get all balances associated with an account, it may be necessary to perform multiple balance requests with unique AccountIdentifiers. It is also possible to perform a historical balance lookup (if the server supports it) by passing in an optional BlockIdentifier.", response = AccountBalanceResponse.class, tags = {
      "Account",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Expected response to a valid request", response = AccountBalanceResponse.class),
      @ApiResponse(code = 500, message = "unexpected error", response = Error.class)})
  @RequestMapping(value = "/account/balance",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<AccountBalanceResponse> accountBalance(
      @ApiParam(value = "", required = true) @Valid @RequestBody
          AccountBalanceRequest accountBalanceRequest) {
    if (getRequest().isPresent()) {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          AccountBalanceResponse response;
          PartialBlockIdentifier partialBlockIdentifier = null;
          try {
            partialBlockIdentifier = accountBalanceRequest.getBlockIdentifier();
            if (partialBlockIdentifier == null ||
                (partialBlockIdentifier.getHash() == null &&
                    partialBlockIdentifier.getIndex() == null)) {
              response = getCurrentBalance(accountBalanceRequest);
            } else {
              response = getHistoryBalance(accountBalanceRequest);
            }
          } catch (ItemNotFoundException e) {
            Error error = Constant.newError(Constant.BLOCK_IS_NOT_EXISTS)
                .retriable(false)
                .details(partialBlockIdentifier);
            return ApiUtil.sendError(request, JSON.toJSONString(error));
          } catch (AccountException e) {
            Error error = Constant.newError(Constant.ACCOUNT_IS_NOT_EXISTS)
                .retriable(false)
                .details(accountBalanceRequest.getAccountIdentifier());
            return ApiUtil.sendError(request, JSON.toJSONString(error));
          }
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
          return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
      }
    }

    return new ResponseEntity<>(HttpStatus.valueOf(200));
  }

  public AccountBalanceResponse getCurrentBalance(AccountBalanceRequest accountBalanceRequest)
      throws AccountException {
    try {
      manager.setCursor(Chainbase.Cursor.SOLIDITY);
      AccountIdentifier accountIdentifier = accountBalanceRequest.getAccountIdentifier();
      if (accountIdentifier == null) {
        throw new AccountException();
      }

      String address = accountIdentifier.getAddress();
      byte[] addressBytes = Commons.decodeFromBase58Check(address);
      if (ArrayUtils.isEmpty(addressBytes)) {
        throw new AccountException();
      }
      AccountCapsule accountCapsule = accountStore.get(addressBytes);
      String balance = String.valueOf(accountCapsule.getBalance());
      Amount amount = new Amount();
      amount.value(balance)
          .currency(Default.CURRENCY);

      BlockIdentifier blockIdentifier = new BlockIdentifier();
      blockIdentifier.index(dynamicPropertiesStore.getLatestBlockHeaderNumber())
          .hash(dynamicPropertiesStore.getLatestBlockHeaderHash().toString());
      AccountBalanceResponse response = new AccountBalanceResponse();
      response.blockIdentifier(blockIdentifier)
          .addBalancesItem(amount);
      return response;
    } finally {
      manager.setCursor(Chainbase.Cursor.HEAD);
    }
  }

  public AccountBalanceResponse getHistoryBalance(AccountBalanceRequest accountBalanceRequest)
      throws AccountException, ItemNotFoundException {
    AccountIdentifier accountIdentifier = accountBalanceRequest.getAccountIdentifier();
    if (accountIdentifier == null) {
      throw new AccountException();
    }

    PartialBlockIdentifier partialBlockIdentifier = accountBalanceRequest.getBlockIdentifier();
    Long index = partialBlockIdentifier.getIndex();
    String hash = partialBlockIdentifier.getHash();
    if ((index == null || index < 0) && StringUtils.isEmpty(hash)) {
      throw new ItemNotFoundException();
    }

    if (index == null || index < 0) {
      index = new BlockCapsule.BlockId(Sha256Hash.wrap(ByteArray.fromHexString(hash))).getNum();
    }
    if (StringUtils.isEmpty(hash)) {
      hash = blockIndexStore.get(index).toString();
    }
    BlockIdentifier blockIdentifier = new BlockIdentifier()
        .index(index)
        .hash(hash);

    String address = accountIdentifier.getAddress();
    byte[] addressBytes = Commons.decodeFromBase58Check(address);
    if (ArrayUtils.isEmpty(addressBytes)) {
      throw new AccountException();
    }

    Pair<Long, Long> pair = accountTraceStore.getPrevBalance(addressBytes, index);
    Amount amount = new Amount()
        .currency(Default.CURRENCY)
        .value(Long.toString(pair.getRight()));

    AccountBalanceResponse response = new AccountBalanceResponse();
    response.blockIdentifier(blockIdentifier)
        .addBalancesItem(amount);
    return response;
  }
}
