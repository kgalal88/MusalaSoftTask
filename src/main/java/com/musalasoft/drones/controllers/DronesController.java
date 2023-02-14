package com.musalasoft.drones.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<ResponseDTO<DroneDTO>> listDrones(
			@PathVariable(name = "page") int page,
			@PathVariable(name = "pageSize") int pageSize) {
		
		ResponseDTO<DroneDTO> responseDTO;
		try {
			responseDTO = dronesService.listDrones(page, pageSize);
			
//			responseDTO.setResult(dronesService.paginateResult(page, pageSize, responseDTO, responseDTO.getResult()));
			
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
