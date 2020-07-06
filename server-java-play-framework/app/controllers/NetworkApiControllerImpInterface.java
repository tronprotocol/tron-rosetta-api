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

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface NetworkApiControllerImpInterface {
    NetworkListResponse networkList(MetadataRequest metadataRequest) throws Exception;

    NetworkOptionsResponse networkOptions(NetworkRequest networkRequest) throws Exception;

    NetworkStatusResponse networkStatus(NetworkRequest networkRequest) throws Exception;

}
