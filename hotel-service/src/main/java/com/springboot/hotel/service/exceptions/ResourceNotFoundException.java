package com.springboot.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	ResourceNotFoundException(){
		super("Resource not found");
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	
}
