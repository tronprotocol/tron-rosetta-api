package controllers;

import apimodels.Error;
import apimodels.MetadataRequest;
import apimodels.NetworkListResponse;
import apimodels.NetworkOptionsResponse;
import apimodels.NetworkRequest;
import apimodels.NetworkStatusResponse;

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

public class NetworkApiController extends Controller {

    private final NetworkApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private NetworkApiController(Configuration configuration, NetworkApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result networkList() throws Exception {
        JsonNode nodemetadataRequest = request().body().asJson();
        MetadataRequest metadataRequest;
        if (nodemetadataRequest != null) {
            metadataRequest = mapper.readValue(nodemetadataRequest.toString(), MetadataRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(metadataRequest);
            }
        } else {
            throw new IllegalArgumentException("'MetadataRequest' parameter is required");
        }
        NetworkListResponse obj = imp.networkList(metadataRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result networkOptions() throws Exception {
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
        NetworkOptionsResponse obj = imp.networkOptions(networkRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result networkStatus() throws Exception {
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
        NetworkStatusResponse obj = imp.networkStatus(networkRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
