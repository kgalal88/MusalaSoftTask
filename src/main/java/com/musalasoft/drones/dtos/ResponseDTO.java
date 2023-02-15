package com.musalasoft.drones.dtos;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author khalid_galal
 *
 */
public class ResponseDTO<T> {
	
	private int status;
	private String message;
	private List<T> result;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
