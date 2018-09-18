/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kriit.spring.validation;

/**
 *
 * @author Viral
 */
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.log4j.Logger;

public class RegExValidator implements ConstraintValidator<ValidRegEx, String> {

    private final static Logger LOGGER = Logger.getLogger(RegExValidator.class);

    private int min;

    @Override
    public void initialize(ValidRegEx validRegEx) {
        min = validRegEx.min();
    }

    @Override
    public boolean isValid(String inputRegEx, ConstraintValidatorContext context) {

        if (inputRegEx == null && inputRegEx.isEmpty()) {
            return false;
        }

        if (inputRegEx.length() < min) {
            return false;
        }

        if (regExValitor(inputRegEx)) {
            return false;
        }

        return true;
    }

    public static Boolean regExValitor(String userInputPattern) {
        try {
            LOGGER.info("::RegExValidator--regExValitor--userInputPattern--->"+userInputPattern);
            Pattern pattern = Pattern.compile(userInputPattern);
        } catch (PatternSyntaxException exception) {
            //If pattern is invalid we will return True 
            return true;
        }
        return false;

    }

}
