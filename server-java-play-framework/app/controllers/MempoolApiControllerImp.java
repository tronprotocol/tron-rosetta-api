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
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

public class MempoolApiControllerImp implements MempoolApiControllerImpInterface {
    @Override
    public MempoolResponse mempool(NetworkRequest networkRequest) throws Exception {
        //Do your magic!!!
        return new MempoolResponse();
    }

    @Override
    public MempoolTransactionResponse mempoolTransaction(MempoolTransactionRequest mempoolTransactionRequest) throws Exception {
        //Do your magic!!!
        return new MempoolTransactionResponse();
    }

}
