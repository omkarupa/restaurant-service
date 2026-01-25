package com.op_solutions.restaurant_service.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}

}
