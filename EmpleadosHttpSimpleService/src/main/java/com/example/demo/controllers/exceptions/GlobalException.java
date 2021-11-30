package com.example.demo.controllers.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler 
	public ResponseEntity<ErrorObject> handleEmployeeNotFoundException (EmployeeNotFoundException ex) {
		ErrorObject eObj = new ErrorObject();
		eObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		eObj.setMessage(ex.getMessage());
		eObj.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(eObj, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleDataNotFoundException (DataNotFoundException ex) {
		ErrorObject eObj = new ErrorObject();
		eObj.setStatusCode(HttpStatus.NO_CONTENT.value());
		eObj.setMessage(ex.getMessage());
		eObj.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(eObj, HttpStatus.OK);
	}
	
	
	
	
	
}
