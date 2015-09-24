package com.equalsp.commons.csv;

import com.equalsp.commons.cvs.validator.TransformValue;

public class TranformYesORNo implements TransformValue{

	@Override
	public Object transform(String value) throws Exception {
		return value.equalsIgnoreCase("SIM") ? "Yes" : "No";
	}

}
