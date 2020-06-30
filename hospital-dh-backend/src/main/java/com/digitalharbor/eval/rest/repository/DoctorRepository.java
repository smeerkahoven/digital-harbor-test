package com.digitalharbor.eval.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.DoctorEntity;
import com.digitalharbor.eval.rest.entity.PacienteEntity;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Integer>{

}
