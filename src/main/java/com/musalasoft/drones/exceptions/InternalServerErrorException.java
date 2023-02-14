package com.musalasoft.drones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author khalid_galal
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="INTERNAL SERVER ERROR")
public class InternalServerErrorException extends Exception {

	public InternalServerErrorException(String messgae) {
		super(messgae);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}