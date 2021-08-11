package org.tron.common;

import org.springframework.util.ObjectUtils;
import org.tron.api.GrpcAPI;
import org.tron.config.Constant;
import org.tron.model.Error;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.tron.api.GrpcAPI.Return.response_code.*;

/**
 * error code util
 */
public class ErrorCodeUtil {

	private static final Set<Integer> TRON_ERROR_CODES = new HashSet<>(Arrays.asList(SIGERROR.getNumber(),
			CONTRACT_VALIDATE_ERROR.getNumber(), CONTRACT_EXE_ERROR.getNumber(),
			BANDWITH_ERROR.getNumber(), DUP_TRANSACTION_ERROR.getNumber(),
			TAPOS_ERROR.getNumber(), TOO_BIG_TRANSACTION_ERROR.getNumber(),
			TRANSACTION_EXPIRATION_ERROR.getNumber(), SERVER_BUSY.getNumber(),
			NO_CONNECTION.getNumber(), NOT_ENOUGH_EFFECTIVE_CONNECTION.getNumber()));

	public static boolean containsTronErrorCode(int errorCode) {
		if (ObjectUtils.isEmpty(errorCode)) {
			return false;
		}
		return TRON_ERROR_CODES.contains(errorCode);
	}

	public static Error getTronError(int errorCode) {
		Error error = Constant.newError(Constant.INTERNAL_SERVER_ERROR);
		if (ObjectUtils.isEmpty(errorCode)) {
			return error;
		}
		GrpcAPI.Return.response_code response_code = forNumber(errorCode);
		switch (response_code) {
			case SIGERROR:
				error = Constant.newError(Constant.SIGERROR_FAILED);
				break;
			case CONTRACT_VALIDATE_ERROR:
				error = Constant.newError(Constant.CONTRACT_VALIDATE_ERROR_FAILED);
				break;
			case CONTRACT_EXE_ERROR:
				error = Constant.newError(Constant.CONTRACT_EXE_ERROR_FAILED);
				break;
			case BANDWITH_ERROR:
				error = Constant.newError(Constant.BANDWITH_ERROR_FAILED);
				break;
			case DUP_TRANSACTION_ERROR:
				error = Constant.newError(Constant.DUP_TRANSACTION_ERROR_FAILED);
				break;
			case TAPOS_ERROR:
				error = Constant.newError(Constant.TAPOS_ERROR_FAILED);
				break;
			case TOO_BIG_TRANSACTION_ERROR:
				error = Constant.newError(Constant.TOO_BIG_TRANSACTION_ERROR_FAILED);
				break;
			case TRANSACTION_EXPIRATION_ERROR:
				error = Constant.newError(Constant.TRANSACTION_EXPIRATION_FAILED);
			case SERVER_BUSY:
				error = Constant.newError(Constant.SERVER_BUSY_FAILED);
			case NO_CONNECTION:
				error = Constant.newError(Constant.NO_CONNECTION_FAILED);
			case NOT_ENOUGH_EFFECTIVE_CONNECTION:
				error = Constant.newError(Constant.NOT_ENOUGH_EFFECTIVE_CONNECTION_FAILED);
				break;
			default:
		}
		return error;
	}


}
