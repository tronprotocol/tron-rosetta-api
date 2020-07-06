package controllers;

import apimodels.ConstructionCombineRequest;
import apimodels.ConstructionCombineResponse;
import apimodels.ConstructionDeriveRequest;
import apimodels.ConstructionDeriveResponse;
import apimodels.ConstructionHashRequest;
import apimodels.ConstructionHashResponse;
import apimodels.ConstructionMetadataRequest;
import apimodels.ConstructionMetadataResponse;
import apimodels.ConstructionParseRequest;
import apimodels.ConstructionParseResponse;
import apimodels.ConstructionPayloadsRequest;
import apimodels.ConstructionPayloadsResponse;
import apimodels.ConstructionPreprocessRequest;
import apimodels.ConstructionPreprocessResponse;
import apimodels.ConstructionSubmitRequest;
import apimodels.ConstructionSubmitResponse;
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

public class ConstructionApiController extends Controller {

    private final ConstructionApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private ConstructionApiController(Configuration configuration, ConstructionApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result constructionCombine() throws Exception {
        JsonNode nodeconstructionCombineRequest = request().body().asJson();
        ConstructionCombineRequest constructionCombineRequest;
        if (nodeconstructionCombineRequest != null) {
            constructionCombineRequest = mapper.readValue(nodeconstructionCombineRequest.toString(), ConstructionCombineRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionCombineRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionCombineRequest' parameter is required");
        }
        ConstructionCombineResponse obj = imp.constructionCombine(constructionCombineRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionDerive() throws Exception {
        JsonNode nodeconstructionDeriveRequest = request().body().asJson();
        ConstructionDeriveRequest constructionDeriveRequest;
        if (nodeconstructionDeriveRequest != null) {
            constructionDeriveRequest = mapper.readValue(nodeconstructionDeriveRequest.toString(), ConstructionDeriveRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionDeriveRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionDeriveRequest' parameter is required");
        }
        ConstructionDeriveResponse obj = imp.constructionDerive(constructionDeriveRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionHash() throws Exception {
        JsonNode nodeconstructionHashRequest = request().body().asJson();
        ConstructionHashRequest constructionHashRequest;
        if (nodeconstructionHashRequest != null) {
            constructionHashRequest = mapper.readValue(nodeconstructionHashRequest.toString(), ConstructionHashRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionHashRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionHashRequest' parameter is required");
        }
        ConstructionHashResponse obj = imp.constructionHash(constructionHashRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionMetadata() throws Exception {
        JsonNode nodeconstructionMetadataRequest = request().body().asJson();
        ConstructionMetadataRequest constructionMetadataRequest;
        if (nodeconstructionMetadataRequest != null) {
            constructionMetadataRequest = mapper.readValue(nodeconstructionMetadataRequest.toString(), ConstructionMetadataRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionMetadataRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionMetadataRequest' parameter is required");
        }
        ConstructionMetadataResponse obj = imp.constructionMetadata(constructionMetadataRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionParse() throws Exception {
        JsonNode nodeconstructionParseRequest = request().body().asJson();
        ConstructionParseRequest constructionParseRequest;
        if (nodeconstructionParseRequest != null) {
            constructionParseRequest = mapper.readValue(nodeconstructionParseRequest.toString(), ConstructionParseRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionParseRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionParseRequest' parameter is required");
        }
        ConstructionParseResponse obj = imp.constructionParse(constructionParseRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionPayloads() throws Exception {
        JsonNode nodeconstructionPayloadsRequest = request().body().asJson();
        ConstructionPayloadsRequest constructionPayloadsRequest;
        if (nodeconstructionPayloadsRequest != null) {
            constructionPayloadsRequest = mapper.readValue(nodeconstructionPayloadsRequest.toString(), ConstructionPayloadsRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionPayloadsRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionPayloadsRequest' parameter is required");
        }
        ConstructionPayloadsResponse obj = imp.constructionPayloads(constructionPayloadsRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionPreprocess() throws Exception {
        JsonNode nodeconstructionPreprocessRequest = request().body().asJson();
        ConstructionPreprocessRequest constructionPreprocessRequest;
        if (nodeconstructionPreprocessRequest != null) {
            constructionPreprocessRequest = mapper.readValue(nodeconstructionPreprocessRequest.toString(), ConstructionPreprocessRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionPreprocessRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionPreprocessRequest' parameter is required");
        }
        ConstructionPreprocessResponse obj = imp.constructionPreprocess(constructionPreprocessRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result constructionSubmit() throws Exception {
        JsonNode nodeconstructionSubmitRequest = request().body().asJson();
        ConstructionSubmitRequest constructionSubmitRequest;
        if (nodeconstructionSubmitRequest != null) {
            constructionSubmitRequest = mapper.readValue(nodeconstructionSubmitRequest.toString(), ConstructionSubmitRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(constructionSubmitRequest);
            }
        } else {
            throw new IllegalArgumentException("'ConstructionSubmitRequest' parameter is required");
        }
        ConstructionSubmitResponse obj = imp.constructionSubmit(constructionSubmitRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
