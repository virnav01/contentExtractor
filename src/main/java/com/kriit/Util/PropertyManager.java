/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.Util;

/**
 *
 * @author Viral
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

public class PropertyManager {

   private static final Logger LOGGER = Logger.getLogger(PropertyManager.class.getName());

    public static Properties fetchProperties(){
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:SFApplication.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }
    
    
    public static void main(String[] args) throws IOException {
        PropertyManager obj = new PropertyManager();
        Properties properties = PropertyManager.fetchProperties();
        // get the each property value using getProperty() method 
        System.out.println("email: " + properties.getProperty("EMAIL_REGX"));
    }
    
}
