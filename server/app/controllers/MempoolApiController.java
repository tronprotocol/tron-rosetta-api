package controllers;

import apimodels.Error;
import apimodels.MempoolResponse;
import apimodels.MempoolTransactionRequest;
import apimodels.MempoolTransactionResponse;
import apimodels.NetworkRequest;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import java.io.File;
import openapitools.OpenAPIUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.validation.constraints.*;
import play.Configuration;

import openapitools.OpenAPIUtils.ApiAction;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

public class MempoolApiController extends Controller {

    private final MempoolApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private MempoolApiController(Configuration configuration, MempoolApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result mempool() throws Exception {
        JsonNode nodenetworkRequest = request().body().asJson();
        NetworkRequest networkRequest;
        if (nodenetworkRequest != null) {
            networkRequest = mapper.readValue(nodenetworkRequest.toString(), NetworkRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(networkRequest);
            }
        } else {
            throw new IllegalArgumentException("'NetworkRequest' parameter is required");
        }
        MempoolResponse obj = imp.mempool(networkRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result mempoolTransaction() throws Exception {
        JsonNode nodemempoolTransactionRequest = request().body().asJson();
        MempoolTransactionRequest mempoolTransactionRequest;
        if (nodemempoolTransactionRequest != null) {
            mempoolTransactionRequest = mapper.readValue(nodemempoolTransactionRequest.toString(), MempoolTransactionRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(mempoolTransactionRequest);
            }
        } else {
            throw new IllegalArgumentException("'MempoolTransactionRequest' parameter is required");
        }
        MempoolTransactionResponse obj = imp.mempoolTransaction(mempoolTransactionRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
