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
public class PDFException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;
   
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public PDFException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    public PDFException(String errorMessage,String status) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public PDFException() {
        super();
    }
}
