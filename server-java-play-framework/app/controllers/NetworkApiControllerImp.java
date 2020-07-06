package controllers;

import apimodels.Error;
import apimodels.MetadataRequest;
import apimodels.NetworkListResponse;
import apimodels.NetworkOptionsResponse;
import apimodels.NetworkRequest;
import apimodels.NetworkStatusResponse;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

public class NetworkApiControllerImp implements NetworkApiControllerImpInterface {
    @Override
    public NetworkListResponse networkList(MetadataRequest metadataRequest) throws Exception {
        //Do your magic!!!
        return new NetworkListResponse();
    }

    @Override
    public NetworkOptionsResponse networkOptions(NetworkRequest networkRequest) throws Exception {
        //Do your magic!!!
        return new NetworkOptionsResponse();
    }

    @Override
    public NetworkStatusResponse networkStatus(NetworkRequest networkRequest) throws Exception {
        //Do your magic!!!
        return new NetworkStatusResponse();
    }

}
