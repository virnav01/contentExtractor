/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Viral
 */
public class SFInputByType {

    @NotNull(message = CommonMessages.INVALID_REQ_ID_NOT_NULL)
    @Size(min = 3, max = 18, message = CommonMessages.INVALID_REQ_ID_LENGTH)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = CommonMessages.INVALID_REQ_ID_REGEX)
    private String RequestID;

    @Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$", message = CommonMessages.INVALID_CONTENT)
    @Size(max = 5000000, message = CommonMessages.INVALID_PDF_SIZE)
    private String Content;

    
    @Pattern(regexp = "pdf|PDF|DOC|doc|docx|DOCX",  message = CommonMessages.INVALID_TYPE)
    private String Type;

    
    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String RequestID) {
        this.RequestID = RequestID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

}
