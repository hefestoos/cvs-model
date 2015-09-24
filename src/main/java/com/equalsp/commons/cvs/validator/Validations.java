package com.equalsp.commons.cvs.validator;

public enum Validations {

	NOTNULL {
		@Override
		boolean validate(String value) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, NUMBER {
		@Override
		boolean validate(String value) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, EMAIL {
		@Override
		boolean validate(String value) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, 
	CUSTOM {
		@Override
		boolean validate(String value) {
			return true;
		}

		@Override
		String id() {
			return null;
		}
	};
	
	abstract String id();
	
	String message(String fieldName){
		return "The field "+fieldName+" not is valid for "+this.id();
	}
	
	abstract boolean validate(String value);
	
}
