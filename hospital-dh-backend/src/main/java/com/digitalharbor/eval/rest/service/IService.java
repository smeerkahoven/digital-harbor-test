package com.digitalharbor.eval.rest.service;

import java.util.List;

import com.digitalharbor.eval.rest.exception.HospitalException;

public interface IService<T> {
	
	public T create(T dto) throws HospitalException ;
	
	public T update (T dto) throws HospitalException ;
	
	public boolean delete (T dto) throws HospitalException ;
	
	public List<T> get (Integer id) throws HospitalException ;
	
	public List<T> get() throws HospitalException ;
	
	public boolean isValid(T dto) throws HospitalException;

}
