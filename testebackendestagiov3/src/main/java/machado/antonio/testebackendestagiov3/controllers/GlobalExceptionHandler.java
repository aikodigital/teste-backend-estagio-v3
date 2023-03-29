package machado.antonio.testebackendestagiov3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;

/**
 * Handles declared exceptions so that the user/client/developer can understand
 * what was the error and how to solve it with a message from the exception and
 * with a HTTP status.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CannotRequestNullException.class)
	public ResponseEntity<?> handleCannotRequestNullAttributeException(CannotRequestNullException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<?> handleEntityExistsException(EntityExistsException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

}
