package controllers;

import apimodels.Error;
import apimodels.MempoolResponse;
import apimodels.MempoolTransactionRequest;
import apimodels.MempoolTransactionResponse;
import apimodels.NetworkRequest;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface MempoolApiControllerImpInterface {
    MempoolResponse mempool(NetworkRequest networkRequest) throws Exception;

    MempoolTransactionResponse mempoolTransaction(MempoolTransactionRequest mempoolTransactionRequest) throws Exception;

}
