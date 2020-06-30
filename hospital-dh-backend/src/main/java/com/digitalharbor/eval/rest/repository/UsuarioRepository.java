package com.digitalharbor.eval.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalharbor.eval.rest.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer>{
	
	public UsuarioEntity findByUsername(String username) ;
	
	public UsuarioEntity findByPublicId(String publicId);

}
