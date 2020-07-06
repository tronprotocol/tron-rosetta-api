package com.prokarma.pkmst.controller;

import com.prokarma.pkmst.model.Error;
import com.prokarma.pkmst.model.MetadataRequest;
import com.prokarma.pkmst.model.NetworkListResponse;
import com.prokarma.pkmst.model.NetworkOptionsResponse;
import com.prokarma.pkmst.model.NetworkRequest;
import com.prokarma.pkmst.model.NetworkStatusResponse;

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
public class NetworkApiController implements NetworkApi {
    private final ObjectMapper objectMapper;
@Autowired
    public NetworkApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<NetworkListResponse> networkList(@ApiParam(value = "" ,required=true )   @RequestBody MetadataRequest metadataRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<NetworkListResponse>(objectMapper.readValue("", NetworkListResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<NetworkListResponse>(HttpStatus.OK);
    }

    public ResponseEntity<NetworkOptionsResponse> networkOptions(@ApiParam(value = "" ,required=true )   @RequestBody NetworkRequest networkRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<NetworkOptionsResponse>(objectMapper.readValue("", NetworkOptionsResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<NetworkOptionsResponse>(HttpStatus.OK);
    }

    public ResponseEntity<NetworkStatusResponse> networkStatus(@ApiParam(value = "" ,required=true )   @RequestBody NetworkRequest networkRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<NetworkStatusResponse>(objectMapper.readValue("", NetworkStatusResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<NetworkStatusResponse>(HttpStatus.OK);
    }

}
