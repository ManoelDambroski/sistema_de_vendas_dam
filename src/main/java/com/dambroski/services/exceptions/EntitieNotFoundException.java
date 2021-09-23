package com.dambroski.services.exceptions;


public class EntitieNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntitieNotFoundException(String msg) {
		super(msg);
	}
}