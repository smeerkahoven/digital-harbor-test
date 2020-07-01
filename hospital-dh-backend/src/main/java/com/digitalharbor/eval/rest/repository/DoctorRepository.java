package com.digitalharbor.eval.rest.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.DoctorEntity;
import com.digitalharbor.eval.rest.entity.HospitalEntity;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Integer>{
	
	public List<DoctorEntity> findByHospitalId(HospitalEntity id) ;
}
