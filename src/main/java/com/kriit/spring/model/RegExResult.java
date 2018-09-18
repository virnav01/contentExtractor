/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Viral
 */
public class RegExResult {

    private String REQ_ID;

    private Map<String, List<String>> Result;

    public String getREQ_ID() {
        return REQ_ID;
    }

    public void setREQ_ID(String REQ_ID) {
        this.REQ_ID = REQ_ID;
    }

    public Map<String, List<String>> getResult() {
        return Result;
    }

    public void setResult(Map<String, List<String>> Result) {
        this.Result = Result;
    }

    @Override
    public String toString() {
        return "RegExResult{" + "key=" + REQ_ID + ", Result Size=" + String.valueOf(Result != null ? -1 : Result.size()) + '}';
    }

}
