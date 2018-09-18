/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

import com.kriit.spring.validation.ValidRegEx;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Viral
 */
public class RegEX {

    @NotNull(message = CommonMessages.INVALID_REGX_NAME_NOT_NULL)
    @Size(min = 3, max = 18, message = CommonMessages.INVALID_REGX_NAME_LENGTH)
    private String Key;

    @NotNull(message = CommonMessages.INVALID_REGX_NOT_NULL)
    @ValidRegEx
    private String Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    @Override
    public String toString() {
        return "RegEX{" + "key=" + Key + ", value=" + Value + '}';
    }
}
