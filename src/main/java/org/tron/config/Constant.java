package org.tron.config;

import java.util.Arrays;
import java.util.List;

import org.tron.model.Error;
import org.tron.model.OperationStatus;

public class Constant {

  public static final String rosettaVersion = "1.4.0";
  public static final String middlewareVersion = "1.0.2";

  // OperationStatus
  public static OperationStatus OPERATION_DEFAULT = new OperationStatus().status("DEFAULT").successful(true);
  public static OperationStatus OPERATION_SUCCESS = new OperationStatus().status("SUCCESS").successful(true);
  public static OperationStatus OPERATION_REVERT = new OperationStatus().status("REVERT").successful(false);
  public static OperationStatus OPERATION_BAD_JUMP_DESTINATION = new OperationStatus().status("BAD_JUMP_DESTINATION").successful(false);
  public static OperationStatus OPERATION_OUT_OF_MEMORY = new OperationStatus().status("OUT_OF_MEMORY").successful(false);
  public static OperationStatus OPERATION_PRECOMPILED_CONTRACT = new OperationStatus().status("PRECOMPILED_CONTRACT").successful(false);
  public static OperationStatus OPERATION_STACK_TOO_SMALL = new OperationStatus().status("STACK_TOO_SMALL").successful(false);
  public static OperationStatus OPERATION_STACK_TOO_LARGE = new OperationStatus().status("STACK_TOO_LARGE").successful(false);
  public static OperationStatus OPERATION_ILLEGAL_OPERATION = new OperationStatus().status("ILLEGAL_OPERATION").successful(false);
  public static OperationStatus OPERATION_STACK_OVERFLOW = new OperationStatus().status("STACK_OVERFLOW").successful(false);
  public static OperationStatus OPERATION_OUT_OF_ENERGY = new OperationStatus().status("OUT_OF_ENERGY").successful(false);
  public static OperationStatus OPERATION_OUT_OF_TIME = new OperationStatus().status("OUT_OF_TIME").successful(false);
  public static OperationStatus OPERATION_JVM_STACK_OVER_FLOW = new OperationStatus().status("JVM_STACK_OVER_FLOW").successful(false);
  public static OperationStatus OPERATION_UNKNOWN = new OperationStatus().status("UNKNOWN").successful(false);
  public static OperationStatus OPERATION_TRANSFER_FAILED = new OperationStatus().status("TRANSFER_FAILED").successful(false);

  // errors
  public static Error INVALID_ACCOUNT_FORMAT =
      new Error().code(12).message("Invalid account format").retriable(true).details(null);
  public static Error INVALID_TRANSACTION_FORMAT =
      new Error().code(100).message("Invalid transaction format").retriable(false).details(null);
  public static Error BROADCAST_TRANSACTION_FAILED =
      new Error().code(101).message("Broadcast transaction failed").retriable(false).details(null);
  public static Error BLOCK_ID_OVER_CURRENT_LAST =
      new Error().code(201).message("Block ID is bigger than current latest block").retriable(true).details(null);
  public static Error SERVER_EXCEPTION_CATCH =
      new Error().code(202).message("exception catch").retriable(false).details(null);


  public static String[] supportOperationTypes = new String[]{
      "AccountCreateContract",
      "TransferContract",
      "TransferAssetContract",
      "VoteAssetContract",
      "VoteWitnessContract",
      "WitnessCreateContract",
      "AssetIssueContract",
      "WitnessUpdateContract",
      "ParticipateAssetIssueContract",
      "AccountUpdateContract",
      "FreezeBalanceContract",
      "UnfreezeBalanceContract",
      "WithdrawBalanceContract",
      "UnfreezeAssetContract",
      "UpdateAssetContract",
      "ProposalCreateContract",
      "ProposalApproveContract",
      "ProposalDeleteContract",
      "SetAccountIdContract",
      "CustomContract",
      "CreateSmartContract",
      "TriggerSmartContract",
      "GetContract",
      "UpdateSettingContract",
      "ExchangeCreateContract",
      "ExchangeInjectContract",
      "ExchangeWithdrawContract",
      "ExchangeTransactionContract",
      "UpdateEnergyLimitContract",
      "AccountPermissionUpdateContract",
      "ClearABIContract",
      "UpdateBrokerageContract",
      "ShieldedTransferContract",
      "MarketSellAssetContract",
      "MarketCancelOrderContract",
      "UNRECOGNIZED"
  };
  public static List<OperationStatus> supportOperationStatuses = Arrays.asList(
      OPERATION_DEFAULT,
      OPERATION_SUCCESS,
      OPERATION_REVERT,
      OPERATION_BAD_JUMP_DESTINATION,
      OPERATION_OUT_OF_MEMORY,
      OPERATION_PRECOMPILED_CONTRACT,
      OPERATION_STACK_TOO_SMALL,
      OPERATION_STACK_TOO_LARGE,
      OPERATION_ILLEGAL_OPERATION,
      OPERATION_STACK_OVERFLOW,
      OPERATION_OUT_OF_ENERGY,
      OPERATION_OUT_OF_TIME,
      OPERATION_JVM_STACK_OVER_FLOW,
      OPERATION_UNKNOWN,
      OPERATION_TRANSFER_FAILED
  );
  public static List<Error> supportErrors = Arrays.asList(
      INVALID_ACCOUNT_FORMAT,
      INVALID_TRANSACTION_FORMAT,
      BROADCAST_TRANSACTION_FAILED,
      BLOCK_ID_OVER_CURRENT_LAST,
      SERVER_EXCEPTION_CATCH
  );
}
