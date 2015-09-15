package com.equalsp.commons.cvs.validator;


public class StaticValidator implements Validator{
	
	ValidationsEnum validation;
	
	public StaticValidator(ValidationsEnum validation){
		this.validation = validation;
	}

	public String name() {
		return validation.id();
	}

	public String message(String fieldName) {
		return validation.message(fieldName);
	}

	public Boolean validate(String value) {
		return validation.validate(value);
	}
	
}
