package com.karuntiple.blog_app_api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	String fieldValue;

	public UserNameNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s :%s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
