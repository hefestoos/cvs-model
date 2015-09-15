package com.equalsp.commons.cvs.validator;

public enum ValidationsEnum {

	NOTNULL {
		@Override
		Boolean validate(String value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, NUMBER {
		@Override
		Boolean validate(String value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, EMAIL {
		@Override
		Boolean validate(String value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String id() {
			// TODO Auto-generated method stub
			return null;
		}

	}, 
	CUSTOM {
		@Override
		Boolean validate(String value) {
			return null;
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
	
	abstract Boolean validate(String value);
	
}
