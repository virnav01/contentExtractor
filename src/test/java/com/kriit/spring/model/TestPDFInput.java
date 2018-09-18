/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Viral
 */
public class TestPDFInput {

    public static void main(String[] args) throws IOException {

        PDFInput pdf = new PDFInput();
        pdf.setPDF("jkswgkeadhgjeajhjleahlh");
        pdf.setRequestID("abcderfhjshjgefgdf");

        List<RegEX> regxList = new ArrayList<>();

        RegEX reg = new RegEX();
        reg.setKey("Phone");
        reg.setValue("sbfgjetdh");
        regxList.add(reg);

        reg = new RegEX();
        reg.setKey("Email");
        reg.setValue("ehrjkrjyt");
        regxList.add(reg);

        pdf.setRegExList(regxList);

        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // serialize the object
        mapper.writeValue(System.out, pdf);

    }

}
