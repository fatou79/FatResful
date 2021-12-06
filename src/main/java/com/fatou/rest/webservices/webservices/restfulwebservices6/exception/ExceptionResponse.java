package com.fatou.rest.webservices.webservices.restfulwebservices6.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String details;
	
	
	public ExceptionResponse(Date timestamp, String message, String deatails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = deatails;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public String getMessage() {
		return message;
	}


	public String getDeatails() {
		return details;
	}
	
	
	
	
	

}
