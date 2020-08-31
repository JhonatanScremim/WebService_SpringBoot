package com.spring.webservice.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.webservice.services.exceptions.DataBaseException;
import com.spring.webservice.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResouceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) // Mostra que esse metodo vai intercepta qualquer exception do tipo ResourceNotFoundException que for lançada e vai fazer o tratamento no metodo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(er);
	}
	
	@ExceptionHandler(DataBaseException.class) // Mostra que esse metodo vai intercepta qualquer exception do tipo ResourceNotFoundException que for lançada e vai fazer o tratamento no metodo
	public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request){
		String error = "Erro no banco de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(er);
	}
	
}

