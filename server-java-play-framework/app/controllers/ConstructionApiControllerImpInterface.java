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

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface ConstructionApiControllerImpInterface {
    ConstructionCombineResponse constructionCombine(ConstructionCombineRequest constructionCombineRequest) throws Exception;

    ConstructionDeriveResponse constructionDerive(ConstructionDeriveRequest constructionDeriveRequest) throws Exception;

    ConstructionHashResponse constructionHash(ConstructionHashRequest constructionHashRequest) throws Exception;

    ConstructionMetadataResponse constructionMetadata(ConstructionMetadataRequest constructionMetadataRequest) throws Exception;

    ConstructionParseResponse constructionParse(ConstructionParseRequest constructionParseRequest) throws Exception;

    ConstructionPayloadsResponse constructionPayloads(ConstructionPayloadsRequest constructionPayloadsRequest) throws Exception;

    ConstructionPreprocessResponse constructionPreprocess(ConstructionPreprocessRequest constructionPreprocessRequest) throws Exception;

    ConstructionSubmitResponse constructionSubmit(ConstructionSubmitRequest constructionSubmitRequest) throws Exception;

}
