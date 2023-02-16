package com.musalasoft.drones.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.musalasoft.drones.services.MedicationService;

/**
 * 
 * @author khalid_galal
 *
 */
@Service
public class MedicationServiceImpl implements MedicationService {
	
	private final Logger logger = LoggerFactory.getLogger(MedicationServiceImpl.class);

	@Autowired
	private MedicationRepository medicationRepository;	

	@Override
	public ResponseDTO<MedicationDTO> getMedications() throws InternalServerErrorException {

		ResponseDTO<MedicationDTO> responseDTO = new ResponseDTO<MedicationDTO>();
		List<MedicationDTO> result = new ArrayList<>();
		
		List<Medication> medications = (List<Medication>) medicationRepository.findAll();
		
		if(!medications.isEmpty()) {
			medications.forEach(d -> {				
				result.add(toMedicationDTO(d));
			});		
			
			responseDTO.setStatus(HttpStatus.OK.value());
			responseDTO.setMessage("Data retrievd successfully");
			responseDTO.setResult(result);
		}else {
			logger.error("medications is empty!");
		}
		
		if (result.isEmpty()) {
			throw new InternalServerErrorException("result is empty!");
		} else {
			return responseDTO;
		}
	}

	private MedicationDTO toMedicationDTO(Medication d) {
		return new MedicationDTO(d.getName(), d.getWeight(), d.getCode(), d.getImage());
	}
	
}
