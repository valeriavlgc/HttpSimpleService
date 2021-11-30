package com.example.demo.controllers.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	
	  public EmployeeNotFoundException (String message) {
	        super(message);
	  }

}
