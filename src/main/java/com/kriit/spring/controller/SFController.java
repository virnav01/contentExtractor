package com.kriit.spring.controller;

import com.kriit.Util.ContentExtractor;
import com.kriit.spring.model.CommonMessages;
import com.kriit.Util.PDFContentExtractor;
import com.kriit.Util.PropertyManager;
import com.kriit.Util.WordContentExtractor;
import com.kriit.spring.model.SFURIConstatnts;
import com.kriit.spring.model.PDFException;
import com.kriit.spring.model.Output;
import com.kriit.spring.model.RegExResult;
import com.kriit.spring.model.ResponseStatus;
import com.kriit.spring.model.SFInput;
import com.kriit.spring.model.SFInputByType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class SFController {

    private final static Logger LOGGER = Logger.getLogger(APPController.class);

    private static final Properties PROP = PropertyManager.fetchProperties();
    private static final String EMAIL_REGX = PROP.getProperty("EMAIL_REGX");
    private static final String PHONE_REGX = PROP.getProperty("PHONE_REGEX");

    
    
    //Extract Content for PDF
    @RequestMapping(value = SFURIConstatnts.GET_PDF_CONTENT, method = RequestMethod.POST)
    public @ResponseBody
    Output getSFAttachedPDFContents(@Valid @RequestBody SFInput input, Errors errors) {

        LOGGER.info("Start SFController--->getSFAtatchmentDetails()");

        LOGGER.info("Start SFController--->getSFAtatchmentDetails()-->input RequestID::" + input.getRequestID());

        Output out;
        ResponseStatus rs;

        if (errors.hasErrors()) {

            LOGGER.info("Start SFController--->getSFAtatchmentDetails()--> Found Errors");

            out = new Output();
            rs = new ResponseStatus();
            rs.setResponseCode(500);
            rs.setStatus(CommonMessages.ERROR);
            rs.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            out.setStatus(rs);

        } else {

            out = new Output();
            rs = new ResponseStatus();

            RegExResult regResult = new RegExResult();
            Map<String, List<String>> RegExResultMAP = new HashMap<>();

            PDFContentExtractor pe = new PDFContentExtractor();
            String pdfContent = pe.getPDFContent(input.getPDF());

            if (pdfContent != null) {

                rs.setResponseCode(200);
                rs.setStatus(CommonMessages.OK);
                rs.setMessage(CommonMessages.SUC_MSG);

                ArrayList<String> phone = ContentExtractor.extractContent(pdfContent, PHONE_REGX);
                ArrayList<String> email = ContentExtractor.extractContent(pdfContent, EMAIL_REGX);

                RegExResultMAP.put("Phone", phone);
                RegExResultMAP.put("Email", email);

            } else {
                rs.setResponseCode(500);
                rs.setStatus(CommonMessages.ERROR);
                rs.setMessage(CommonMessages.ERR_MSG);
            }

            regResult.setResult(RegExResultMAP);
            regResult.setREQ_ID(input.getRequestID());

            out.setRegExResult(regResult);
            out.setStatus(rs);
        }

        LOGGER.info("End SFController--->getSFAtatchmentDetails()");

        return out;
    }

    
    
    
    
    
    //Extract Content through Input variable Type (DOC/PDF).
    @RequestMapping(value = SFURIConstatnts.GET_CONTENT_BY_TYPE, method = RequestMethod.POST)
    public @ResponseBody
    Output getSFAtatchmentContents(@Valid @RequestBody SFInputByType input, Errors errors) {

        LOGGER.info("Start SFController--->getSFAtatchmentContents()");
        LOGGER.info("Start SFController--->getSFAtatchmentContents()-->input RequestID::" + input.getRequestID());

        Output out;
        ResponseStatus rs;

        if (errors.hasErrors()) {

            LOGGER.info("Start SFController--->getSFAtatchmentContents()--> Found Errors");

            out = new Output();
            rs = new ResponseStatus();
            rs.setResponseCode(500);
            rs.setStatus(CommonMessages.ERROR);
            rs.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            out.setStatus(rs);

        } else {

            out = new Output();
            rs = new ResponseStatus();

            RegExResult regResult = new RegExResult();
            Map<String, List<String>> RegExResultMAP = new HashMap<>();

            PDFContentExtractor pe = new PDFContentExtractor();
            WordContentExtractor we = new WordContentExtractor();

            //Get the string
            String Content = null;

            if (input.getType().equalsIgnoreCase("PDF")) {
                Content = pe.getPDFContent(input.getContent());
            } else if (input.getType().equalsIgnoreCase("DOC") || input.getType().equalsIgnoreCase("DOCX")) {
                Content = we.getDocContent(input.getContent());
            }

            if (Content != null) {

                rs.setResponseCode(200);
                rs.setStatus(CommonMessages.OK);
                rs.setMessage(CommonMessages.SUC_MSG);

                ArrayList<String> phone = ContentExtractor.extractContent(Content, PHONE_REGX);
                ArrayList<String> email = ContentExtractor.extractContent(Content, EMAIL_REGX);

                RegExResultMAP.put("Phone", phone);
                RegExResultMAP.put("Email", email);

            } else {
                rs.setResponseCode(500);
                rs.setStatus(CommonMessages.ERROR);
                rs.setMessage(CommonMessages.ERR_MSG);
            }

            regResult.setResult(RegExResultMAP);
            regResult.setREQ_ID(input.getRequestID());

            out.setRegExResult(regResult);
            out.setStatus(rs);
        }

        LOGGER.info("End SFController--->getSFAtatchmentContents()");

        return out;
    }

    @ExceptionHandler(PDFException.class)
    public ResponseEntity<ResponseStatus> exceptionHandler(Exception ex) {
        ResponseStatus error = new ResponseStatus();
        error.setResponseCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
