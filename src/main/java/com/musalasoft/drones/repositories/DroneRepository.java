package com.musalasoft.drones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musalasoft.drones.entities.Drone;

/**
 * 
 * @author khalid_galal
 *
 */
@Repository
public interface DroneRepository extends CrudRepository<Drone, Integer> {
	
	Drone findDroneById(Integer id);	
}