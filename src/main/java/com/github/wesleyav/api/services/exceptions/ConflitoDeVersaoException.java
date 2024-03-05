package com.github.wesleyav.api.services.exceptions;

public class ConflitoDeVersaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflitoDeVersaoException() {
		super();
	}

	public ConflitoDeVersaoException(String msg) {
		super(msg);
	}
}
