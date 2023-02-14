package com.musalasoft.drones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author khalid_galal
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="FORBIDDEN")
public class ForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}