package com.musalasoft.drones.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.entities.Medication;

/**
 * 
 * @author khalid_galal
 *
 */
@Repository
public interface MedicationRepository extends CrudRepository<Medication, Integer> {

}