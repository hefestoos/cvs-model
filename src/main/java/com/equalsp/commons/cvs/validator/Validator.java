package com.equalsp.commons.cvs.validator;

public interface Validator {
	
	String name();
	
	String message(String string);

	Boolean validate(String value);

}
