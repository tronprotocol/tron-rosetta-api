package com.prokarma.pkmst.controller;

import com.prokarma.pkmst.model.Error;
import com.prokarma.pkmst.model.MempoolResponse;
import com.prokarma.pkmst.model.MempoolTransactionRequest;
import com.prokarma.pkmst.model.MempoolTransactionResponse;
import com.prokarma.pkmst.model.NetworkRequest;

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
public class MempoolApiController implements MempoolApi {
    private final ObjectMapper objectMapper;
@Autowired
    public MempoolApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<MempoolResponse> mempool(@ApiParam(value = "" ,required=true )   @RequestBody NetworkRequest networkRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<MempoolResponse>(objectMapper.readValue("", MempoolResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<MempoolResponse>(HttpStatus.OK);
    }

    public ResponseEntity<MempoolTransactionResponse> mempoolTransaction(@ApiParam(value = "" ,required=true )   @RequestBody MempoolTransactionRequest mempoolTransactionRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<MempoolTransactionResponse>(objectMapper.readValue("", MempoolTransactionResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<MempoolTransactionResponse>(HttpStatus.OK);
    }

}
