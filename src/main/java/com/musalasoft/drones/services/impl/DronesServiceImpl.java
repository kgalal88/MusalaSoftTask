package com.musalasoft.drones.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.exceptions.InternalServerErrorException;
import com.musalasoft.drones.repositories.DroneRepository;
import com.musalasoft.drones.services.DronesService;

/**
 * 
 * @author khalid_galal
 *
 */
@Service
public class DronesServiceImpl implements DronesService {
	
	private final Logger logger = LoggerFactory.getLogger(DronesServiceImpl.class);

	@Autowired
	private DroneRepository droneRepository;	

	@Override
	public ResponseDTO<DroneDTO> listDrones(int page, int pageSize) throws InternalServerErrorException {

		ResponseDTO<DroneDTO> responseDTO = new ResponseDTO<DroneDTO>();
		List<DroneDTO> result = new ArrayList<>();
		
		List<Drone> drones = (List<Drone>) droneRepository.findAll();

		
		if(!drones.isEmpty()) {
			drones.forEach(d -> {
				result.add(new DroneDTO(d.getSerialNumber(), d.getModel(), d.getWeightLimit(), d.getBatteryCapacity(), d.getState()));
			});		
			
			responseDTO.setStatus(HttpStatus.OK.value());
			responseDTO.setMessage("Data retrievd successfully");
			responseDTO.setResult(result);
		}else {
			logger.error("drones is empty!");
		}
		
		if (result.isEmpty()) {
			throw new InternalServerErrorException("result is empty!");
		} else {
			return responseDTO;
		}
	}
}
