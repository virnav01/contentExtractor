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

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import javax.validation.Constraint;
import javax.validation.Payload;
 
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = com.kriit.spring.validation.RegExValidator.class)
@Documented
public @interface ValidRegEx {
 
	String message() default "Not a valid Regular Expression";
 
	Class<?>[] groups() default {};
 
	Class<? extends Payload>[] payload() default {};
 
	int min() default 5;
}