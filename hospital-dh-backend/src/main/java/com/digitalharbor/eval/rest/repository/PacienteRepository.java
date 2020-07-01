package com.digitalharbor.eval.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.HospitalEntity;
import com.digitalharbor.eval.rest.entity.PacienteEntity;

@Repository
public interface PacienteRepository extends CrudRepository<PacienteEntity, Integer>{
	
	public List<PacienteEntity> findByHospitalId(HospitalEntity id) ;

}
