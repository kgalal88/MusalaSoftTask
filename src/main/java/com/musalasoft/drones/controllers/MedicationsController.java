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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.MedicationDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.exceptions.InternalServerErrorException;
import com.musalasoft.drones.services.DronesService;
import com.musalasoft.drones.services.MedicationService;

/**
 * 
 * @author khalid_galal
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/medications")
public class MedicationsController {
	
	private final Logger logger = LoggerFactory.getLogger(MedicationsController.class);

	@Autowired
	private MedicationService medicationService;

	@GetMapping("/")
	public ResponseEntity<ResponseDTO<MedicationDTO>> getMedications() {
		
		ResponseDTO<MedicationDTO> responseDTO;
		try {
			responseDTO = medicationService.getMedications();
						
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
