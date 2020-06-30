package com.digitalharbor.eval.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.HospitalEntity;

@Repository
public interface HospitalRepository extends CrudRepository<HospitalEntity, Integer>{

	public List<HospitalEntity> findByNombre(String nombre) ;
	
	
	
}
