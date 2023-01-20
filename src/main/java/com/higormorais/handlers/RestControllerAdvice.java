package com.higormorais.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.higormorais.handlers.messages.ErrorMessage;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handlerEntityNotFound(EntityNotFoundException exception) {
		var error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), exception.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorMessage> handlerBindException(BindException exception) {
		var error = new ErrorMessage("Bad request", HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	

}
