package com.github.wesleyav.api.services.exceptions;

public class DebitoExcedeLimiteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DebitoExcedeLimiteException() {
		super();
	}

	public DebitoExcedeLimiteException(String string) {
		super(string);
	}
}
