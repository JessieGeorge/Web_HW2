package edu.csula.cs5220stu14.hw2.web;

import java.sql.SQLIntegrityConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(
			ControllerExceptionHandler.class);

	@ExceptionHandler(org.hibernate.PropertyValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<String> showCustomMessage(Exception e) {
		logger.error("Missing required field?", e);
		String message = "Missing required field? " + e.getMessage();
		return new ResponseEntity<String>(message, 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<String> handleConstraintViolation(Exception e) {
		logger.error("Existing group name?", e);
		String message = "Existing group name? " + e.getMessage();
		return new ResponseEntity<String>(message, 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<String> handleFormatException(
			InvalidFormatException ex) {
		logger.error("Serialization/Deserialization failed.", ex);
		return new ResponseEntity<String>("Wrong data format.", 
				HttpStatus.BAD_REQUEST);
	}
}