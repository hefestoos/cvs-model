package com.equalsp.commons.cvs;

public class ConfigurationException extends Exception {

	public ConfigurationException(String msg, Exception e) {
		super(msg, e);
	}

	public ConfigurationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 4097662751754671210L;

}
