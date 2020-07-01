package com.digitalharbor.eval.rest.service;

import java.util.List;

import com.digitalharbor.eval.rest.exception.HospitalException;

public interface IDoctorService<T> extends IService<T> {
	
	public List<T> search(T dto) throws HospitalException ;
	public List<T> getByHospital(Integer id) throws HospitalException ;

}
