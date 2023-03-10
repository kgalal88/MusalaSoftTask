package com.musalasoft.drones.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musalasoft.drones.entities.Drone;

/**
 * 
 * @author khalid_galal
 *
 */
@Repository
public interface DroneRepository extends CrudRepository<Drone, String> {
		
	List<Drone> findDroneBySerialNumber(String serialNumber);
	
	List<Drone> findDroneByState(String state);
}