package com.digitalharbor.eval.rest.service;

import java.util.List;

import com.digitalharbor.eval.rest.exception.HospitalException;

public interface IEspecialidadService<T> extends IService<T> {
	
	public List<T> getByHospital(Integer id) throws HospitalException;

}
