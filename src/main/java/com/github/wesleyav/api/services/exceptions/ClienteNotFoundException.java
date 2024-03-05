package com.github.wesleyav.api.services.exceptions;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(String string) {
		super(string);
	}

}
