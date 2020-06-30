package com.digitalharbor.eval.rest.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.digitalharbor.eval.rest.exception.HospitalException;

public interface IUsuarioService<T> extends IService<T>, UserDetailsService {
	
	T getUsuarioByUsername(String username) throws HospitalException ;
	
	T getUsuarioByPublicId(String publicId) throws HospitalException ;

}
