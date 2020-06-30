package com.digitalharbor.eval.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.NotaEntity;

@Repository
public interface NotaRepository extends CrudRepository<NotaEntity, Integer>{

}
