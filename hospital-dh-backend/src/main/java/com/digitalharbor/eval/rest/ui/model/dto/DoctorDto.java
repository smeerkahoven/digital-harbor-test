package com.digitalharbor.eval.rest.ui.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class DoctorDto extends PersonaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private HospitalDto hospital ;
	
	private List<EspecialidadDto> especialidades ;

}
