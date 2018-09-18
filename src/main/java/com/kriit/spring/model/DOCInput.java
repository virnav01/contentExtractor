/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Viral
 */
public class DOCInput {

    @NotNull(message = CommonMessages.INVALID_REQ_ID_NOT_NULL)
    @Size(min = 3, max = 18, message = CommonMessages.INVALID_REQ_ID_LENGTH)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = CommonMessages.INVALID_REQ_ID_REGEX)
    private String RequestID;

    @Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$", message = CommonMessages.INVALID_DOC)
    @Size(max = 5000000, message = CommonMessages.INVALID_DOC_SIZE)
    private String DOC;

    @NotNull
    @Valid
    @Size(min = 1, max = 3, message = CommonMessages.MAX_OUT_REGXS)
    private List<RegEX> regExList;

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String RequestID) {
        this.RequestID = RequestID;
    }

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public List<RegEX> getRegExList() {
        return regExList;
    }

    public void setRegExList(List<RegEX> regExList) {
        this.regExList = regExList;
    }

}
