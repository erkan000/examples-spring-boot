package com.tictactoe.game.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GameGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception ex, WebRequest request){
		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request){
		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handle(MethodArgumentNotValidException ex, WebRequest request){
		GameErrorResponse err = new GameErrorResponse();
		err.setMessage(ex.getMessage());
		err.setTime(LocalDateTime.now().toString());
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);
	}

}
