package com.ratiose.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;


public class RequestException extends HttpStatusCodeException
{

	public RequestException(HttpStatus status, String message) {
		super(status, message);

	}

}
