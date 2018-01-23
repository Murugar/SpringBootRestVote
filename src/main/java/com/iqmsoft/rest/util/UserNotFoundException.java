package com.iqmsoft.rest.util;

public class UserNotFoundException extends RuntimeException {
	
	private String message;
	private Throwable th;
	
	public UserNotFoundException(String message)
	{
		super(message);
		this.message = message;
	}
	
	public UserNotFoundException(String message, Throwable th)
	{
		super(message, th);
		this.message = message;
		this.th = th;
		
	}
	
	

}

