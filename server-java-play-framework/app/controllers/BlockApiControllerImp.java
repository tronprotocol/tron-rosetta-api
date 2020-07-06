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
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

public class BlockApiControllerImp implements BlockApiControllerImpInterface {
    @Override
    public BlockResponse block(BlockRequest blockRequest) throws Exception {
        //Do your magic!!!
        return new BlockResponse();
    }

    @Override
    public BlockTransactionResponse blockTransaction(BlockTransactionRequest blockTransactionRequest) throws Exception {
        //Do your magic!!!
        return new BlockTransactionResponse();
    }

}
