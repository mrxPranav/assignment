package com.pranav.assignment.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@RestControllerAdvice
public class AdviceController {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> tokenExpiredException(UsernameNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BlankCredentialsException.class)
	public ResponseEntity<String> blankCred(BlankCredentialsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
