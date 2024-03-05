package com.github.wesleyav.api.services.exceptions;

public class PayloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PayloadException() {
		super();
	}

	public PayloadException(String msg) {
		super(msg);
	}
}
