package com.github.wesleyav.api.controllers.exceptions;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.wesleyav.api.services.exceptions.ClienteNotFoundException;
import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;
import com.github.wesleyav.api.services.exceptions.TipoTransacaoInvalidoException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoNuloException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoPositivoException;

import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(PayloadException.class)
//	public ResponseEntity<StandardError> payloadException(PayloadException e, HttpServletRequest request) {
//		String error = "Payload fora da especificação";
//		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
//
//		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
//				request.getRequestURI());
//		return ResponseEntity.status(status).body(standardError);
//	}

	@ExceptionHandler(ValorTransacaoPositivoException.class)
	public ResponseEntity<StandardError> valorTransacaoPositivoException(ValorTransacaoPositivoException e,
			HttpServletRequest request) {
		String error = "O atributo valor deve ser um número inteiro positivo.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(ValorTransacaoNuloException.class)
	public ResponseEntity<StandardError> valorTransacaoNuloException(ValorTransacaoNuloException e,
			HttpServletRequest request) {
		String error = "O atributo valor não pode ser nulo.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(TipoTransacaoInvalidoException.class)
	public ResponseEntity<StandardError> tipoTransacaoInvalidoException(TipoTransacaoInvalidoException e,
			HttpServletRequest request) {
		String error = "O tipo da transação deve ser 'c' para crédito ou 'd' para débito.";
		HttpStatus status = HttpStatus.BAD_REQUEST;

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
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

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