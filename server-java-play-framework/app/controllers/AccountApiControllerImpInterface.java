package controllers;

import apimodels.AccountBalanceRequest;
import apimodels.AccountBalanceResponse;
import apimodels.Error;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface AccountApiControllerImpInterface {
    AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) throws Exception;

}
