package com.musalasoft.drones.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.exceptions.InternalServerErrorException;
import com.musalasoft.drones.services.DronesService;

/**
 * 
 * @author khalid_galal
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/drones")
public class DronesController {
	
	private final Logger logger = LoggerFactory.getLogger(DronesController.class);

	@Autowired
	private DronesService dronesService;

	@GetMapping("/list")
	public ResponseEntity<ResponseDTO<DroneDTO>> listDrones() {
		
		ResponseDTO<DroneDTO> responseDTO;
		try {
			responseDTO = dronesService.listDrones();
						
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} catch (InternalServerErrorException e) {
			logger.error("ERROR", e);			
			responseDTO = new ResponseDTO<>();
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/{serialNumber}")
	public ResponseEntity<ResponseDTO<DroneDTO>> getDroneMedications(@PathVariable(name = "serialNumber") String serialNumber) {
		
		ResponseDTO<DroneDTO> responseDTO;
		try {
			responseDTO = dronesService.getDroneMedications(serialNumber);
						
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} catch (InternalServerErrorException e) {
			logger.error("ERROR", e);			
			responseDTO = new ResponseDTO<>();
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO<DroneDTO>> registerDrone(@RequestBody DroneDTO droneDTO) {
		
		ResponseDTO<DroneDTO> responseDTO;
		try {
			responseDTO = dronesService.registerDrone(droneDTO);
						
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} catch (InternalServerErrorException e) {
			logger.error("ERROR", e);			
			responseDTO = new ResponseDTO<>();
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}
