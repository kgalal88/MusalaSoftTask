package com.musalasoft.drones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.musalasoft.drones.entities.Drone;
import com.musalasoft.drones.entities.Medication;

/**
 * 
 * @author khalid_galal
 *
 */
@Repository
public interface MedicationRepository extends CrudRepository<Medication, String> {

	@Query("select m from Medication m where m.code in :codes" )
	List<Medication> findMedicationByCodes(@Param("codes") List<String> codes);
	
}