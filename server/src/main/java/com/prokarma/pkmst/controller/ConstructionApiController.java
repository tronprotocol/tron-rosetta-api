package com.prokarma.pkmst.controller;

import com.prokarma.pkmst.model.ConstructionCombineRequest;
import com.prokarma.pkmst.model.ConstructionCombineResponse;
import com.prokarma.pkmst.model.ConstructionDeriveRequest;
import com.prokarma.pkmst.model.ConstructionDeriveResponse;
import com.prokarma.pkmst.model.ConstructionHashRequest;
import com.prokarma.pkmst.model.ConstructionHashResponse;
import com.prokarma.pkmst.model.ConstructionMetadataRequest;
import com.prokarma.pkmst.model.ConstructionMetadataResponse;
import com.prokarma.pkmst.model.ConstructionParseRequest;
import com.prokarma.pkmst.model.ConstructionParseResponse;
import com.prokarma.pkmst.model.ConstructionPayloadsRequest;
import com.prokarma.pkmst.model.ConstructionPayloadsResponse;
import com.prokarma.pkmst.model.ConstructionPreprocessRequest;
import com.prokarma.pkmst.model.ConstructionPreprocessResponse;
import com.prokarma.pkmst.model.ConstructionSubmitRequest;
import com.prokarma.pkmst.model.ConstructionSubmitResponse;
import com.prokarma.pkmst.model.Error;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
/**
 * Api implemention
 * @author pkmst
 *
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPKMSTServerCodegen", date = "2020-07-01T16:38:10.259179+08:00[Asia/Shanghai]")

@Controller
public class ConstructionApiController implements ConstructionApi {
    private final ObjectMapper objectMapper;
@Autowired
    public ConstructionApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<ConstructionCombineResponse> constructionCombine(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionCombineRequest constructionCombineRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionCombineResponse>(objectMapper.readValue("", ConstructionCombineResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionCombineResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionDeriveResponse> constructionDerive(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionDeriveRequest constructionDeriveRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionDeriveResponse>(objectMapper.readValue("", ConstructionDeriveResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionDeriveResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionHashResponse> constructionHash(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionHashRequest constructionHashRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionHashResponse>(objectMapper.readValue("", ConstructionHashResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionHashResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionMetadataResponse> constructionMetadata(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionMetadataRequest constructionMetadataRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionMetadataResponse>(objectMapper.readValue("", ConstructionMetadataResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionMetadataResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionParseResponse> constructionParse(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionParseRequest constructionParseRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionParseResponse>(objectMapper.readValue("", ConstructionParseResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionParseResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionPayloadsResponse> constructionPayloads(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionPayloadsRequest constructionPayloadsRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionPayloadsResponse>(objectMapper.readValue("", ConstructionPayloadsResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionPayloadsResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionPreprocessResponse> constructionPreprocess(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionPreprocessRequest constructionPreprocessRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionPreprocessResponse>(objectMapper.readValue("", ConstructionPreprocessResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionPreprocessResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ConstructionSubmitResponse> constructionSubmit(@ApiParam(value = "" ,required=true )   @RequestBody ConstructionSubmitRequest constructionSubmitRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<ConstructionSubmitResponse>(objectMapper.readValue("", ConstructionSubmitResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<ConstructionSubmitResponse>(HttpStatus.OK);
    }

}
