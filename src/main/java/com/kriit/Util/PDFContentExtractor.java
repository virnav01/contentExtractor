/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.log4j.Logger;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;

/**
 *
 * @author Viral
 */
public class PDFContentExtractor {

    

    final static Logger LOGGER = Logger.getLogger(PDFContentExtractor.class);

    public String getPDFContent(String input) {
      
        LOGGER.info("Start PDFContentExtractor--->getPDFContent()");
        String parsedText;
        try {
            
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(input);
            
            //Parse PDF that is a valid (otherwise an IOException occurs)
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(new ByteArrayInputStream(decodedBytes)));
            parser.parse();
            
            //Extract Text from Parsed Document
            PDFTextStripper pdfStripper = new PDFTextStripper();
            parsedText = pdfStripper.getText(new PDDocument(parser.getDocument()));
            
            
        } catch (IOException ex) {
            parsedText = null;
            LOGGER.info("Exception :: PDFContentExtractor-->getPDFContent()::" + ex.getMessage());
        }
        LOGGER.info("PDF String Length:" + String.valueOf(parsedText != null ? parsedText.length() : 0));
        LOGGER.info("Ends PDFContentExtractor--->getContent()");
        return parsedText;

    }

    public static void main(String[] args) {
        String PDF = "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Pj4KZW5kb2JqCnhyZWYKMCAxNQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyNTcgMDAwMDAgbiAKMDAwMDAxNjc5MiAwMDAwMCBuIAowMDAwMDAwMDA5IDAwMDAwIG4gCjAwMDAwMDAxMTcgMDAwMDAgbiAKMDAwMDAwMDM0NCAwMDAwMCBuIAowMDAwMDAwODk4IDAwMDAwIG4gCjAwMDAwMDA5MjggMDAwMDAgbiAKMDAwMDAwMDk1OCAwMDAwMCBuIAowMDAwMDAxMDIzIDAwMDAwIG4gCjAwMDAwMDE0MDEgMDAwMDAgbiAKMDAwMDAwMTYyNiAwMDAwMCBuIAowMDAwMDAxOTcwIDAwMDAwIG4gCjAwMDAwMTY4OTggMDAwMDAgbiAKMDAwMDAxNzAwNiAwMDAwMCBuIAp0cmFpbGVyCjw8Ci9TaXplIDE1Ci9Sb290IDE0IDAgUgovSW5mbyAxMyAwIFIKL0lEIFs8OTI5QzU2NUFEQzlFRERCNzM0OTFERTc2NjE2NTU4NkE+PDkyOUM1NjVBREM5RUREQjczNDkxREU3NjYxNjU1ODZBPl0KPj4Kc3RhcnR4cmVmCjE3MTEwCiUlRU9GCg==";
        PDFContentExtractor pe = new PDFContentExtractor();
        System.out.println("PDFContentExtractor --> Main -->PDF Content::" + pe.getPDFContent(PDF));

    }

}
