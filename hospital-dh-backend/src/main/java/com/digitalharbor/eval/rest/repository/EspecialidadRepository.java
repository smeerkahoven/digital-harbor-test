package com.digitalharbor.eval.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.EspecialidadEntity;
import com.digitalharbor.eval.rest.entity.HospitalEntity;

@Repository
public interface EspecialidadRepository extends CrudRepository<EspecialidadEntity, Integer> {
	
	public List<EspecialidadEntity> findByHospitalId(HospitalEntity id) ;

}
