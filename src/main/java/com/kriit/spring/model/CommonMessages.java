/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

/**
 *
 * @author Viral
 */
public class CommonMessages {

    public static final String OK = "OK";
    public static final String SUC_MSG = "Request submitted succesfully Kindly check the result.";
    public static final String ERR_MSG = "ERROR OCCURED, Unable to Parse Content.";
    public static final String ERROR = "ERROR";

    public static final String INVALID_ID_LENGTH = "Invalid ID field length.";
    public static final String INVALID_ID_REGEX = "Please provide valid input for ID field do avoid special characters.";
    public static final String INVALID_ID_NOT_NULL = "ID can't be Null.";

    public static final String INVALID_REQ_ID_LENGTH = "Invalid Request ID field length should be Min 3 and Max 18";
    public static final String INVALID_REQ_ID_REGEX = "Please provide valid input for Request ID field do avoid special characters.";
    public static final String INVALID_REQ_ID_NOT_NULL = "Request ID can't be Null.";

    public static final String INVALID_PDF = "Invalid BASE64 PDF";
    public static final String INVALID_PDF_SIZE = "Max size of PDF should not be more than 5MB.";
    
    public static final String INVALID_DOC = "Invalid BASE64 DOC";
    public static final String INVALID_DOC_SIZE = "Max size of DOC should not be more than 5MB.";
    
    
    public static final String INVALID_CONTENT = "Invalid value for Content field, It must be BASE64 Encoded string.";
    public static final String INVALID_CONTENT_SIZE = "Max size of Content should not be more than 5MB.";
    
    public static final String INVALID_TYPE = "Invalid value for Type field, It must be (doc/pdf).";

    
    

    public static final String MAX_OUT_REGXS = "Sorry Min 1 and Max 3 Values will be accepted";

    public static final String INVALID_REGX_NAME_NOT_NULL = "Regular Expression Name should not be NULL.";
    public static final String INVALID_REGX_NAME_LENGTH = "Regular Expression Name length should only between 3 to 18.";
    public static final String INVALID_REGX_NOT_NULL = "Regular Expression should not be NULL.";

}
