/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.controller;

import com.kriit.spring.model.CommonMessages;
import com.kriit.spring.model.ErrorOutput;
import com.kriit.spring.model.ResponseStatus;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Viral
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private final static Logger LOGGER = Logger.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorOutput> exceptionHandler(Exception ex) {

        System.out.println("Exception ::"+ex.fillInStackTrace());
        LOGGER.info("Exception Handeled in ExceptionControllerAdvice ::" + Arrays.toString(ex.getStackTrace()));

        ErrorOutput out = new ErrorOutput();

        ResponseStatus error = new ResponseStatus();
        error.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatus(CommonMessages.ERROR);
        error.setMessage("Please contact your administrator");

        out.setStatus(error);

        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
