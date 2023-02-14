package com.musalasoft.drones.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author khalid_galal
 *
 */
@Entity
@Table(name = "drone")
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "serial_number")
	private String serialNumber;
	
	@Column(name = "model")
	private String  model;
	
	@Column(name = "weight_limit")
	private String weightLimit;
	
	@Column(name = "battery_capacity")
	private String batteryCapacity;
	
	@Column(name = "state")
	private String  state;
	
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
