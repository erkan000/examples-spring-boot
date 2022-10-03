package com.tictactoe.game.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GameGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(GameGlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception ex, WebRequest request){
		log.error(ex.getMessage());
		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request){
		log.error(ex.getMessage());
		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		return new ResponseEntity<>(err, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error(ex.getMessage());

		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		
		return new ResponseEntity<>(err, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}
