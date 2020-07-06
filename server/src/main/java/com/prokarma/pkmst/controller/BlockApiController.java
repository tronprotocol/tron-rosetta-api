package com.prokarma.pkmst.controller;

import com.prokarma.pkmst.model.BlockRequest;
import com.prokarma.pkmst.model.BlockResponse;
import com.prokarma.pkmst.model.BlockTransactionRequest;
import com.prokarma.pkmst.model.BlockTransactionResponse;
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
public class BlockApiController implements BlockApi {
    private final ObjectMapper objectMapper;
@Autowired
    public BlockApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<BlockResponse> block(@ApiParam(value = "" ,required=true )   @RequestBody BlockRequest blockRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<BlockResponse>(objectMapper.readValue("", BlockResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<BlockResponse>(HttpStatus.OK);
    }

    public ResponseEntity<BlockTransactionResponse> blockTransaction(@ApiParam(value = "" ,required=true )   @RequestBody BlockTransactionRequest blockTransactionRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<BlockTransactionResponse>(objectMapper.readValue("", BlockTransactionResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<BlockTransactionResponse>(HttpStatus.OK);
    }

}
