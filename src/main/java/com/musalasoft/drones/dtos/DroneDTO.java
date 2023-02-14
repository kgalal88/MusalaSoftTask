package com.musalasoft.drones.dtos;

import java.io.Serializable;

/**
 * 
 * @author khalid_galal
 *
 */
public class DroneDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String serialNumber;
	private String  model;
	private String weightLimit;
	private String batteryCapacity;
	private String  state;
	
	
	public DroneDTO(String serialNumber, String model, String weightLimit, String batteryCapacity, String state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(String weightLimit) {
		this.weightLimit = weightLimit;
	}
	public String getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	
	
}
