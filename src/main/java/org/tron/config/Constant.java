package org.tron.config;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.tron.model.Error;
import org.tron.model.OperationStatus;
import org.tron.protos.Protocol;
import org.tron.protos.Protocol.Transaction.Contract.ContractType;
import org.tron.protos.Protocol.Transaction.Result.contractResult;

public class Constant {

  public static final String rosettaVersion = "1.4.0";
  public static final String middlewareVersion = "1.0.2";
  public static final int onlineFieldSize = 96;

  // OperationStatus
  public static OperationStatus OPERATION_DEFAULT = new OperationStatus().status(contractResult.DEFAULT.name()).successful(true);
  public static OperationStatus OPERATION_SUCCESS = new OperationStatus().status(contractResult.SUCCESS.name()).successful(true);
  public static OperationStatus OPERATION_REVERT = new OperationStatus().status(contractResult.REVERT.name()).successful(true);
  public static OperationStatus OPERATION_BAD_JUMP_DESTINATION = new OperationStatus().status(contractResult.BAD_JUMP_DESTINATION.name()).successful(true);
  public static OperationStatus OPERATION_OUT_OF_MEMORY = new OperationStatus().status(contractResult.OUT_OF_MEMORY.name()).successful(true);
  public static OperationStatus OPERATION_PRECOMPILED_CONTRACT = new OperationStatus().status(contractResult.PRECOMPILED_CONTRACT.name()).successful(true);
  public static OperationStatus OPERATION_STACK_TOO_SMALL = new OperationStatus().status(contractResult.STACK_TOO_SMALL.name()).successful(true);
  public static OperationStatus OPERATION_STACK_TOO_LARGE = new OperationStatus().status(contractResult.STACK_TOO_LARGE.name()).successful(true);
  public static OperationStatus OPERATION_ILLEGAL_OPERATION = new OperationStatus().status(contractResult.ILLEGAL_OPERATION.name()).successful(true);
  public static OperationStatus OPERATION_STACK_OVERFLOW = new OperationStatus().status(contractResult.STACK_OVERFLOW.name()).successful(true);
  public static OperationStatus OPERATION_OUT_OF_ENERGY = new OperationStatus().status(contractResult.OUT_OF_ENERGY.name()).successful(true);
  public static OperationStatus OPERATION_OUT_OF_TIME = new OperationStatus().status(contractResult.OUT_OF_TIME.name()).successful(true);
  public static OperationStatus OPERATION_JVM_STACK_OVER_FLOW = new OperationStatus().status(contractResult.JVM_STACK_OVER_FLOW.name()).successful(true);
  public static OperationStatus OPERATION_UNKNOWN = new OperationStatus().status(contractResult.UNKNOWN.name()).successful(true);
  public static OperationStatus OPERATION_TRANSFER_FAILED = new OperationStatus().status(contractResult.TRANSFER_FAILED.name()).successful(true);

  // errors
  public static Error INVALID_REQUEST_FORMAT =
      new Error().code(10).message("Invalid request field").retriable(false).details(null);
  public static Error INTERNAL_SERVER_ERROR =
      new Error().code(11).message("Internal Server Error").retriable(false).details(null);
  public static Error INVALID_ACCOUNT_FORMAT =
      new Error().code(12).message("Invalid account format").retriable(true).details(null);
  public static Error INVALID_TRANSACTION_FORMAT =
      new Error().code(100).message("Invalid transaction format").retriable(false).details(null);
  public static Error BROADCAST_TRANSACTION_FAILED =
      new Error().code(101).message("Broadcast transaction failed").retriable(false).details(null);
  public static Error BLOCK_ID_OVER_CURRENT_LAST =
      new Error().code(201).message("Block ID is bigger than current latest block").retriable(true).details(null);
  public static Error SERVER_EXCEPTION_CATCH =
      new Error().code(202).message("Exception catch").retriable(false).details(null);
  public final static Error BLOCK_IS_NOT_EXISTS =
      new Error().code(203).message("This block does not exists.").retriable(false).details(null);
  public final static Error ACCOUNT_IS_NOT_EXISTS =
      new Error().code(204).message("This account does not exists.").retriable(false).details(null);

  public static Error SIGERROR_FAILED =
      new Error().code(251).message("validate signature error").retriable(false).details(null);
  public static Error CONTRACT_VALIDATE_ERROR_FAILED =
      new Error().code(252).message("contract validate error").retriable(false).details(null);
  public static Error CONTRACT_EXE_ERROR_FAILED =
      new Error().code(253).message("contract execute error").retriable(false).details(null);
  public static Error BANDWITH_ERROR_FAILED =
      new Error().code(254).message("AccountResourceInsufficient error").retriable(false).details(null);
  public static Error DUP_TRANSACTION_ERROR_FAILED =
      new Error().code(255).message("dup transaction").retriable(false).details(null);
  public static Error TAPOS_ERROR_FAILED =
      new Error().code(256).message("Tapos check error").retriable(false).details(null);
  public static Error TOO_BIG_TRANSACTION_ERROR_FAILED =
      new Error().code(257).message("transaction size is too big").retriable(false).details(null);
  public static Error TRANSACTION_EXPIRATION_FAILED =
      new Error().code(258).message("transaction expired").retriable(false).details(null);
  public static Error SERVER_BUSY_FAILED =
      new Error().code(259).message("server busy").retriable(false).details(null);
  public static Error NO_CONNECTION_FAILED =
      new Error().code(260).message("no connection").retriable(false).details(null);
  public static Error NOT_ENOUGH_EFFECTIVE_CONNECTION_FAILED =
      new Error().code(261).message("not enough effective connection").retriable(false).details(null);


  public static Error newError(Error error) {
    return new Error()
        .code(error.getCode())
        .details(error.getDetails())
        .retriable(error.getRetriable())
        .description(error.getDescription())
        .message(error.getMessage());
  }

  public static List<String> supportOperationTypes = getSupportOperationTypes();

  public static List<String> getSupportOperationTypes() {
    List<String> supportOperationTypes = Stream.of(ContractType.values())
        .filter(e -> e != ContractType.UNRECOGNIZED)
        .map(ContractType::name)
        .collect(Collectors.toList());
    supportOperationTypes.add("Fee");
    supportOperationTypes.add("GenesisTransferContract");
    return supportOperationTypes;
  }

  public static List<OperationStatus> supportOperationStatuses = Stream.of(contractResult.values())
      .filter(e -> e != contractResult.UNRECOGNIZED)
      .map(contractResult::name)
      .map(e -> new OperationStatus().status(e).successful(true))
      .collect(Collectors.toList());

//  public static List<OperationStatus> supportOperationStatuses = Arrays.asList(
//      OPERATION_DEFAULT,
//      OPERATION_SUCCESS,
//      OPERATION_REVERT,
//      OPERATION_BAD_JUMP_DESTINATION,
//      OPERATION_OUT_OF_MEMORY,
//      OPERATION_PRECOMPILED_CONTRACT,
//      OPERATION_STACK_TOO_SMALL,
//      OPERATION_STACK_TOO_LARGE,
//      OPERATION_ILLEGAL_OPERATION,
//      OPERATION_STACK_OVERFLOW,
//      OPERATION_OUT_OF_ENERGY,
//      OPERATION_OUT_OF_TIME,
//      OPERATION_JVM_STACK_OVER_FLOW,
//      OPERATION_UNKNOWN,
//      OPERATION_TRANSFER_FAILED
//  );
  public static List<Error> supportErrors = Arrays.asList(
    INVALID_REQUEST_FORMAT,
    INTERNAL_SERVER_ERROR,
    INVALID_ACCOUNT_FORMAT,
    INVALID_TRANSACTION_FORMAT,
    BROADCAST_TRANSACTION_FAILED,
    BLOCK_ID_OVER_CURRENT_LAST,
    SERVER_EXCEPTION_CATCH,
    BLOCK_IS_NOT_EXISTS,
    ACCOUNT_IS_NOT_EXISTS,
    SIGERROR_FAILED,
    CONTRACT_VALIDATE_ERROR_FAILED,
    CONTRACT_EXE_ERROR_FAILED,
    BANDWITH_ERROR_FAILED,
    DUP_TRANSACTION_ERROR_FAILED,
    TAPOS_ERROR_FAILED,
    TOO_BIG_TRANSACTION_ERROR_FAILED,
    TRANSACTION_EXPIRATION_FAILED,
    SERVER_BUSY_FAILED,
    NO_CONNECTION_FAILED,
    NOT_ENOUGH_EFFECTIVE_CONNECTION_FAILED
);
}
