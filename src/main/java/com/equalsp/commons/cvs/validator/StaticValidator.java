package com.equalsp.commons.cvs.validator;


public class StaticValidator implements Validator{
	
	Validations validation;
	
	public StaticValidator(Validations validation){
		this.validation = validation;
	}

	public String name() {
		return validation.id();
	}

	public String message(String fieldName) {
		return validation.message(fieldName);
	}

	public boolean validate(String value) {
		return validation.validate(value);
	}
	
}
