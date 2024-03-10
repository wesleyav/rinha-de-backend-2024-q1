package com.github.wesleyav.api.controllers.exceptions;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.wesleyav.api.services.exceptions.ClienteNotFoundException;
import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;
import com.github.wesleyav.api.services.exceptions.UnprocessableEntityException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		String error = "Payload inválido";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<StandardError> unprocessableEntityException(UnprocessableEntityException e,
			HttpServletRequest request) {
		String error = "Payload inválido";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e,
			HttpServletRequest request) {
		String error = "Payload inválido";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException e,
			HttpServletRequest request) {
		String error = "O id do cliente deve ser entre 1 e 6.";
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> nullPointerException(NullPointerException e, HttpServletRequest request) {
		String error = "A descrição não pode ser nula.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest request) {
		String error = "A descrição da transação deve ter entre 1 e 10 caracteres.";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<StandardError> clienteNotFoundException(ClienteNotFoundException e,
			HttpServletRequest request) {
		String error = "Cliente não encontrado.";
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DebitoExcedeLimiteException.class)
	public ResponseEntity<StandardError> debitoExcedeLimiteException(DebitoExcedeLimiteException e,
			HttpServletRequest request) {
		String error = "A transação de débito excede o limite disponível do cliente.";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DebitoCausaSaldoNegativoException.class)
	public ResponseEntity<StandardError> debitoCausaSaldoNegativoException(DebitoCausaSaldoNegativoException e,
			HttpServletRequest request) {
		String error = "A transação de débito causa saldo negativo do cliente.";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(OptimisticLockException.class)
	public ResponseEntity<StandardError> optimisticLockException(OptimisticLockException e,
			HttpServletRequest request) {
		String error = "Conflito de versão ao atualizar a transação.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso não existe na base de dados.";
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

}