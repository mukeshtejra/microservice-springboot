package com.springboot.hotel.service.exceptions;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	private String message;
	private boolean success;
	private HttpStatus status;
	
	ApiResponse(){
		
	}
	

	public ApiResponse(String message, boolean success, HttpStatus status) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
	}




}
