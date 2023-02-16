package com.musalasoft.drones.services;

import org.springframework.stereotype.Service;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.exceptions.InternalServerErrorException;


/**
 * 
 * @author khalid_galal
 *
 */
@Service
public interface DronesService {
	public ResponseDTO<DroneDTO> getDrones(String serialNumber, String state) throws InternalServerErrorException;
	public ResponseDTO<DroneDTO> registerDrone(DroneDTO droneDTO) throws InternalServerErrorException;
}
