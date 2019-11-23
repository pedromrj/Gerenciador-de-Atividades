package br.com.unifacisa.pp.gym.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.unifacisa.pp.gym.services.UsernameNotFoundException;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDefault> positionValidException(NotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ErrorDefault(HttpStatus.NOT_FOUND.value(),System.currentTimeMillis(),e.getMessage())
				);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDefault> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		//Adiciona os erros na lista.
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorDefault> authorization(AuthorizationException e, HttpServletRequest request) {

		ErrorDefault err = new ErrorDefault(HttpStatus.FORBIDDEN.value(), System.currentTimeMillis(), e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDefault> authorization(UsernameNotFoundException e, HttpServletRequest request) {

		ErrorDefault err = new ErrorDefault(HttpStatus.FORBIDDEN.value(), System.currentTimeMillis(), e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
}
