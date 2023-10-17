package com.satff.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class StaffNotFoundException extends Exception {

	public StaffNotFoundException(String message) 
	{
		super(message);
	}
	
}
