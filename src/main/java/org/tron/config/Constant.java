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
  public static OperationStatus OPERATION_REVERT = new OperationStatus().status("REVERTED").successful(false);

  // errors
  public static Error INVALID_ACCOUNT_FORMAT =
          new Error().code(12).message("Invalid account format").retriable(true).details(null);
  public static Error INVALID_TRANSACTION_FORMAT =
      new Error().code(100).message("Invalid transaction format").retriable(false).details(null);
  public static Error BLOCK_ID_OVER_CURRENT_LAST =
      new Error().code(400).message("Block ID is bigger than current latest block").retriable(true).details(null);
  public static Error SERVER_INTERNAL_ERROR =
      new Error().code(500).message("server internal error").retriable(false).details(null);


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
          OPERATION_REVERT
  );
  public static List<Error> supportErrors = Arrays.asList(
          INVALID_ACCOUNT_FORMAT,
          INVALID_TRANSACTION_FORMAT
  );
}
