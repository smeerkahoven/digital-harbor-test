package com.digitalharbor.eval.rest.ui.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.* ;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PacienteDto extends PersonaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HospitalDto hospital ;

}
