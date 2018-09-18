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
public class Output {

    
    private RegExResult RegExResult;
    
    private ResponseStatus status;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
    
    
    public RegExResult getRegExResult() {
        return RegExResult;
    }

    public void setRegExResult(RegExResult RegExResult) {
        this.RegExResult = RegExResult;
    }
   
}
