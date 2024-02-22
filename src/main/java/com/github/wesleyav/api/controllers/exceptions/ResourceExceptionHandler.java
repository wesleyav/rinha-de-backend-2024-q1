package com.github.wesleyav.api.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.DescricaoTransacaoInvalidaException;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;
import com.github.wesleyav.api.services.exceptions.TipoTransacaoInvalidoException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoPositivoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DebitoCausaSaldoNegativoException.class)
	public ResponseEntity<StandardError> resourceNotFound(DebitoCausaSaldoNegativoException e,
			HttpServletRequest request) {
		String error = "A transação de débito causará um saldo negativo no cliente.";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DebitoExcedeLimiteException.class)
	public ResponseEntity<StandardError> resourceNotFound(DebitoExcedeLimiteException e, HttpServletRequest request) {
		String error = "A transação de débito excede o limite disponível do cliente.";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DescricaoTransacaoInvalidaException.class)
	public ResponseEntity<StandardError> resourceNotFound(DescricaoTransacaoInvalidaException e,
			HttpServletRequest request) {
		String error = "A descrição da transação deve ter entre 1 e 10 caracteres.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(TipoTransacaoInvalidoException.class)
	public ResponseEntity<StandardError> resourceNotFound(TipoTransacaoInvalidoException e,
			HttpServletRequest request) {
		String error = "O tipo da transação deve ser 'c' para crédito ou 'd' para débito.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(ValorTransacaoPositivoException.class)
	public ResponseEntity<StandardError> resourceNotFound(ValorTransacaoPositivoException e,
			HttpServletRequest request) {
		String error = "O valor da transação deve ser um número inteiro positivo.";
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
