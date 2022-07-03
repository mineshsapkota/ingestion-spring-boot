package com.assignment.product.exception;

import java.util.Date;

public class ErrorDetails {
	private Integer status;
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Integer status, Date timestamp, String message, String details) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
