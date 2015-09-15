package com.equalsp.commons.cvs.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.equalsp.commons.cvs.validator.StaticValidator;
import com.equalsp.commons.cvs.validator.ValidationsEnum;

@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
	
	ValidationsEnum[] types();
	
	Class<?> validatorClass() default StaticValidator.class;
	
}
