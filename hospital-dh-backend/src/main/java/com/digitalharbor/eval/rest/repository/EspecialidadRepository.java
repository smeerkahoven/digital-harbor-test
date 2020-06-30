package com.digitalharbor.eval.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.EspecialidadEntity;

@Repository
public interface EspecialidadRepository extends CrudRepository<EspecialidadEntity, Integer> {

}
