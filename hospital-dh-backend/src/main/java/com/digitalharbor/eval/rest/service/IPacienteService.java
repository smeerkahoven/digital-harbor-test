package com.digitalharbor.eval.rest.service;

import java.util.List;

import com.digitalharbor.eval.rest.exception.HospitalException;

public interface IPacienteService<T>  extends IService<T> {
	
	public  List<T> search (T dto) throws HospitalException ;

}
