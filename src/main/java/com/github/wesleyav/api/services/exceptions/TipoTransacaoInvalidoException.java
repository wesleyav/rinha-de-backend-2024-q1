package com.github.wesleyav.api.services.exceptions;

public class TipoTransacaoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TipoTransacaoInvalidoException() {
		super();
	}

	public TipoTransacaoInvalidoException(String string) {
		super(string);
	}
}
