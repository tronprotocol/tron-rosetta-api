package controllers;

import apimodels.BlockRequest;
import apimodels.BlockResponse;
import apimodels.BlockTransactionRequest;
import apimodels.BlockTransactionResponse;
import apimodels.Error;

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

public class BlockApiController extends Controller {

    private final BlockApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private BlockApiController(Configuration configuration, BlockApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result block() throws Exception {
        JsonNode nodeblockRequest = request().body().asJson();
        BlockRequest blockRequest;
        if (nodeblockRequest != null) {
            blockRequest = mapper.readValue(nodeblockRequest.toString(), BlockRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(blockRequest);
            }
        } else {
            throw new IllegalArgumentException("'BlockRequest' parameter is required");
        }
        BlockResponse obj = imp.block(blockRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result blockTransaction() throws Exception {
        JsonNode nodeblockTransactionRequest = request().body().asJson();
        BlockTransactionRequest blockTransactionRequest;
        if (nodeblockTransactionRequest != null) {
            blockTransactionRequest = mapper.readValue(nodeblockTransactionRequest.toString(), BlockTransactionRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(blockTransactionRequest);
            }
        } else {
            throw new IllegalArgumentException("'BlockTransactionRequest' parameter is required");
        }
        BlockTransactionResponse obj = imp.blockTransaction(blockTransactionRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
