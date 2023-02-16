package com.musalasoft.drones.services;

import org.springframework.stereotype.Service;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.MedicationDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.exceptions.InternalServerErrorException;


/**
 * 
 * @author khalid_galal
 *
 */
@Service
public interface MedicationService {
	public ResponseDTO<MedicationDTO> getMedications() throws InternalServerErrorException;
}
