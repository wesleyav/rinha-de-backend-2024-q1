package com.github.wesleyav.api.controllers.exceptions;

public class CommunicationErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommunicationErrorException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunicationErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CommunicationErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommunicationErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommunicationErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
