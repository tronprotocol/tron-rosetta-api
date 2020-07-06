package controllers;

import apimodels.AccountBalanceRequest;
import apimodels.AccountBalanceResponse;
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

public class AccountApiController extends Controller {

    private final AccountApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private AccountApiController(Configuration configuration, AccountApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result accountBalance() throws Exception {
        JsonNode nodeaccountBalanceRequest = request().body().asJson();
        AccountBalanceRequest accountBalanceRequest;
        if (nodeaccountBalanceRequest != null) {
            accountBalanceRequest = mapper.readValue(nodeaccountBalanceRequest.toString(), AccountBalanceRequest.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                OpenAPIUtils.validate(accountBalanceRequest);
            }
        } else {
            throw new IllegalArgumentException("'AccountBalanceRequest' parameter is required");
        }
        AccountBalanceResponse obj = imp.accountBalance(accountBalanceRequest);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
