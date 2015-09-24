package com.equalsp.commons.csv;

import com.equalsp.commons.cvs.validator.TransformValue;

public class EnumTranformValue implements TransformValue {

	@Override
	public Object transform(String value) throws Exception {
		return SomeEnum.valueOf(value); 
	}

}
