package com.musalasoft.drones.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musalasoft.drones.dtos.DroneDTO;
import com.musalasoft.drones.dtos.MedicationDTO;
import com.musalasoft.drones.dtos.ResponseDTO;
import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.entities.Medication;
import com.musalasoft.drones.exceptions.InternalServerErrorException;
import com.musalasoft.drones.repositories.DroneRepository;
import com.musalasoft.drones.repositories.MedicationRepository;
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
	
	@Autowired
	private MedicationRepository medicationRepository;	

	@Override
	public ResponseDTO<DroneDTO> getDrones(String serialNumber, String state) throws InternalServerErrorException {

		ResponseDTO<DroneDTO> responseDTO = new ResponseDTO<DroneDTO>();
		List<DroneDTO> result = new ArrayList<>();
		
		List<Drone> drones = null;
		if(!StringUtils.isEmpty(serialNumber)) {
			drones = (List<Drone>) droneRepository.findDroneBySerialNumber(serialNumber);
		}else if(!StringUtils.isEmpty(state)) {
			drones = (List<Drone>) droneRepository.findDroneByState(state);
		}else {
			drones = (List<Drone>) droneRepository.findAll();
		}
		
		if(!drones.isEmpty()) {
			drones.forEach(d -> {
				DroneDTO droneDTO = toDroneDTO(d);
				
				droneDTO.setMedications(getDroneMedications(d));
				result.add(droneDTO);
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

	private DroneDTO toDroneDTO(Drone d) {
		return new DroneDTO(d.getSerialNumber(), d.getModel(), d.getWeightLimit(), d.getBatteryCapacity(), d.getState());
	}
	
	@Override
	public ResponseDTO<DroneDTO> registerDrone(DroneDTO droneDTO) throws InternalServerErrorException {

		ResponseDTO<DroneDTO> responseDTO = new ResponseDTO<DroneDTO>();
		List<DroneDTO> result = new ArrayList<>();
		
		Drone drone = new Drone(droneDTO.getSerialNumber(), droneDTO.getModel(),
				droneDTO.getWeightLimit(), droneDTO.getBatteryCapacity(), droneDTO.getState());
		
		Drone newDrone = droneRepository.save(drone);
		result = Arrays.asList(toDroneDTO(newDrone));
		
		responseDTO.setStatus(HttpStatus.OK.value());
		responseDTO.setMessage("Data retrievd successfully");
		responseDTO.setResult(result);
		
		return responseDTO;
	}
	
	@Override
	public ResponseDTO<DroneDTO> loadDrone(String serialNumber, List<String> medicationCodes) throws InternalServerErrorException {

		ResponseDTO<DroneDTO> responseDTO = new ResponseDTO<DroneDTO>();
		List<DroneDTO> result = new ArrayList<>();
		String msg = "";
		Drone drone = droneRepository.findDroneBySerialNumber(serialNumber).stream().findFirst().get();	
		if(drone != null) {
			List<Medication> currentMedications = drone.getMedications();
			if(currentMedications == null || currentMedications.isEmpty()) {
				currentMedications = new ArrayList<>();
			}
			
			currentMedications.forEach(m -> {
				if(!StringUtils.isEmpty(m.getCode())) {
					medicationCodes.add(m.getCode());
				}				
			});
			
			medicationCodes.forEach(m -> {
				logger.info(m);
			});
			List<Medication> medications = medicationRepository.findMedicationByCodes(medicationCodes);
						
			AtomicInteger totalWeight = new AtomicInteger(0);
			medications.forEach(m -> {
				if(m != null) {
					totalWeight.addAndGet(Integer.valueOf(m.getWeight()));
				}
			});
				
			
			if(totalWeight.intValue() > Integer.valueOf(drone.getWeightLimit())) {
				msg = "Drone " + drone.getSerialNumber() + " is over weight, drone totalWeight is " + totalWeight + ", while the drone weightLimit is " + drone.getWeightLimit();
				logger.info(msg);
			}else {
				medications.forEach(m -> {
					Medication newMedication = m;
					newMedication.setDrone(drone);
					medicationRepository.save(newMedication);
				});
				drone.setState("LOADED");
				msg = "Drone " + drone.getSerialNumber() + " is loaded successfully, new drone totalWeight is " + totalWeight;
			}
		}
		
		Drone newDrone = droneRepository.save(drone);
		result = Arrays.asList(toDroneDTO(newDrone));
		
		responseDTO.setStatus(HttpStatus.OK.value());
		responseDTO.setMessage(msg);
		responseDTO.setResult(result);
		
		return responseDTO;
	}
	
	@Override
	public ResponseDTO<DroneDTO> unloadDrone(String serialNumber) throws InternalServerErrorException {

		ResponseDTO<DroneDTO> responseDTO = new ResponseDTO<DroneDTO>();
		List<DroneDTO> result = new ArrayList<>();
		String msg = "";
		Drone drone = droneRepository.findDroneBySerialNumber(serialNumber).stream().findFirst().get();	
		if(drone != null) {
			List<Medication> currentMedications = drone.getMedications();
			if(currentMedications == null || currentMedications.isEmpty()) {
				currentMedications = new ArrayList<>();
			}			
			
			currentMedications.forEach(m -> {
					Medication newMedication = m;
					newMedication.setDrone(null);
					medicationRepository.save(newMedication);
				});
			drone.setState("IDLE");
			msg = "Drone " + drone.getSerialNumber() + " is unloaded successfully";
		}
	
		Drone newDrone = droneRepository.save(drone);
		result = Arrays.asList(toDroneDTO(newDrone));
		
		responseDTO.setStatus(HttpStatus.OK.value());
		responseDTO.setMessage(msg);
		responseDTO.setResult(result);
		
		return responseDTO;
	}

	private List<MedicationDTO> getDroneMedications(Drone d) {
		List<MedicationDTO> medicationDTOs = new ArrayList<>();
		if(d.getMedications() != null) {
			d.getMedications().forEach(m -> {
				medicationDTOs.add(new MedicationDTO(m.getName(), m.getWeight(), m.getCode(), m.getImage()));
			});
		}
		return medicationDTOs;
	}
}
