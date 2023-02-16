package com.musalasoft.drones.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.exceptions.InternalServerErrorException;
import com.musalasoft.drones.services.impl.MedicationServiceImpl;

@Component
public class ScheduledTaskService {

	private final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);
	
	@Autowired
	private DronesService dronesService;

	@Scheduled(fixedRate = 10000)
	public void execute() throws InternalServerErrorException {
		logger.info("===== Checking drones battery level =======");		
		ResponseDTO<DroneDTO> responseDTO = dronesService.getDrones(null, null, true);
		responseDTO.getResult().forEach(d -> {
			logger.info("Drone {} has a battery level {}%", d.getSerialNumber(), d.getBatteryCapacity());
		});
	}
}