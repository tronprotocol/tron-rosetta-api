package controllers;

import apimodels.AccountBalanceRequest;
import apimodels.AccountBalanceResponse;
import apimodels.Error;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

public class AccountApiControllerImp implements AccountApiControllerImpInterface {
    @Override
    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) throws Exception {
        //Do your magic!!!
        return new AccountBalanceResponse();
    }

}
