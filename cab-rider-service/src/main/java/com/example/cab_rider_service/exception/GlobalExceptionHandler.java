package com.example.cab_rider_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handleValidationException(MethodArgumentNotValidException ex){

		Map<String,String>errors=new HashMap<>();

		ex.getBindingResult()
		.getAllErrors()
		.forEach(error->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String,String>>handRuntimeException(RuntimeException ex){
		Map<String,String>error=new HashMap<>();

		error.put("error", ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}
}
