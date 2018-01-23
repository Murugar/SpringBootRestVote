package com.iqmsoft.rest.util;

public class RestaurantNotFoundException extends RuntimeException {
	
	private String message;
	private Throwable th;
	
	public RestaurantNotFoundException()
	{
		
	}
	
	public RestaurantNotFoundException(String message)
	{
		super(message);
		this.message = message;
	}
	
	public RestaurantNotFoundException(String message, Throwable th)
	{
		super(message, th);
		this.message = message;
		this.th = th;
		
	}
	
	

}
