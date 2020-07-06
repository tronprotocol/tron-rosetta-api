package com.prokarma.pkmst.controller;

import com.prokarma.pkmst.model.AccountBalanceRequest;
import com.prokarma.pkmst.model.AccountBalanceResponse;
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
public class AccountApiController implements AccountApi {
    private final ObjectMapper objectMapper;
@Autowired
    public AccountApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<AccountBalanceResponse> accountBalance(@ApiParam(value = "" ,required=true )   @RequestBody AccountBalanceRequest accountBalanceRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<AccountBalanceResponse>(objectMapper.readValue("", AccountBalanceResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<AccountBalanceResponse>(HttpStatus.OK);
    }

}
