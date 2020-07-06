package controllers;

import apimodels.BlockRequest;
import apimodels.BlockResponse;
import apimodels.BlockTransactionRequest;
import apimodels.BlockTransactionResponse;
import apimodels.Error;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface BlockApiControllerImpInterface {
    BlockResponse block(BlockRequest blockRequest) throws Exception;

    BlockTransactionResponse blockTransaction(BlockTransactionRequest blockTransactionRequest) throws Exception;

}
