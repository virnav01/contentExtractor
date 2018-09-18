package com.kriit.spring.controller;

import com.kriit.Util.ContentExtractor;
import com.kriit.spring.model.APPURIConstatnts;
import com.kriit.spring.model.CommonMessages;
import com.kriit.Util.PDFContentExtractor;
import com.kriit.Util.WordContentExtractor;
import com.kriit.spring.model.DOCInput;
import com.kriit.spring.model.Input;
import com.kriit.spring.model.PDFException;
import com.kriit.spring.model.PDFInput;
import com.kriit.spring.model.Output;
import com.kriit.spring.model.RegEX;
import com.kriit.spring.model.RegExResult;
import com.kriit.spring.model.ResponseStatus;
import java.text.DateFormat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class APPController {

    private final static Logger LOGGER = Logger.getLogger(APPController.class);

    @RequestMapping(value = APPURIConstatnts.INDEX, method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        System.out.println("Home Page Requested, locale = " + locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home";
    }

    @RequestMapping(value = APPURIConstatnts.GET_PDF_CONTENT_BY_REGEX, method = RequestMethod.POST)
    public @ResponseBody
    Output getAttachedPdfContent(@Valid @RequestBody PDFInput input, Errors errors) {

        LOGGER.info("Start APPController--->getAttachedPdfContent()");

        Output out;
        ResponseStatus rs;

        if (errors.hasErrors()) {
            out = new Output();
            rs = new ResponseStatus();
            rs.setResponseCode(500);
            rs.setStatus(CommonMessages.ERROR);
            rs.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            out.setStatus(rs);
            LOGGER.info("Attachment SFInput has errors");

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

                for (RegEX rx : input.getRegExList()) {
                    ArrayList<String> keyResult = ContentExtractor.extractContent(pdfContent, rx.getValue());
                    RegExResultMAP.put(rx.getKey(), keyResult);
                }
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

        LOGGER.info("End APPController--->getAttachedPdfContent()");

        return out;
    }

    @RequestMapping(value = APPURIConstatnts.GET_DOC_CONTENT_BY_REGEX, method = RequestMethod.POST)
    public @ResponseBody
    Output getAttachedDocContent(@Valid @RequestBody DOCInput input, Errors errors) {

        LOGGER.info("Start APPController--->getAttachedDocContent()");

        Output out;
        ResponseStatus rs;

        if (errors.hasErrors()) {
            out = new Output();
            rs = new ResponseStatus();
            rs.setResponseCode(500);
            rs.setStatus(CommonMessages.ERROR);
            rs.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            out.setStatus(rs);
            LOGGER.info("Attachment SFInput has errors");

        } else {

            out = new Output();
            rs = new ResponseStatus();

            RegExResult regResult = new RegExResult();
            Map<String, List<String>> RegExResultMAP = new HashMap<>();
            WordContentExtractor we = new WordContentExtractor();

            String Content = we.getDocContent(input.getDOC());

            if (Content != null) {

                rs.setResponseCode(200);
                rs.setStatus(CommonMessages.OK);
                rs.setMessage(CommonMessages.SUC_MSG);

                for (RegEX rx : input.getRegExList()) {
                    ArrayList<String> keyResult = ContentExtractor.extractContent(Content, rx.getValue());
                    RegExResultMAP.put(rx.getKey(), keyResult);
                }
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

        LOGGER.info("End APPController--->getAttachedDocContent()");

        return out;
    }

    @RequestMapping(value = APPURIConstatnts.GET_CONTENT_BY_REGEX, method = RequestMethod.POST)
    public @ResponseBody
    Output getAttachedContent(@Valid @RequestBody Input input, Errors errors) {

        LOGGER.info("Start APPController--->getAttachedContent()");

        Output out;
        ResponseStatus rs;

        if (errors.hasErrors()) {
            out = new Output();
            rs = new ResponseStatus();
            rs.setResponseCode(500);
            rs.setStatus(CommonMessages.ERROR);
            rs.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            out.setStatus(rs);
            LOGGER.info("Attachment SFInput has errors");

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

                for (RegEX rx : input.getRegExList()) {
                    ArrayList<String> keyResult = ContentExtractor.extractContent(Content, rx.getValue());
                    RegExResultMAP.put(rx.getKey(), keyResult);
                }
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

        LOGGER.info("End APPController--->getAtatchmentDetails()");

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
