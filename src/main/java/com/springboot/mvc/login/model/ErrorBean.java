package com.springboot.mvc.login.model;

public class ErrorBean {
	
	private String errorMesssage;

	public String getErrorMesssage() {
		return errorMesssage;
	}

	public ErrorBean(String errorMesssage) {
		super();
		this.errorMesssage = errorMesssage;
	}

}
