package com.dambroski.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dambroski.services.exceptions.AuthorizationException;
import com.dambroski.services.exceptions.DataIntegrityException;
import com.dambroski.services.exceptions.EntitieNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EntitieNotFoundException.class)
	public ResponseEntity<StandardError> entitieNotFound(EntitieNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();

		err.setError("Entitie Not Found Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setTimestamp(Instant.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setError("Data integrity exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError("Argument not valid", Instant.now(), HttpStatus.BAD_REQUEST.value(),
				e.getFieldError().toString(), request.getRequestURI());

		for (FieldError x : e.getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		StandardError er = new StandardError();
		er.setError("Authorization exception");
		er.setMessage(e.getMessage());
		er.setPath(request.getRequestURI());
		er.setStatus(HttpStatus.FORBIDDEN.value());
		er.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(er);
	}
	
	
	
}