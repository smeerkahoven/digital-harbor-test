package com.digitalharbor.eval.rest.ui.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class NotaDto extends DefaultDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id ;
	private String fechaAtencion ;
	private String descripcion ;
	private PacienteDto idPaciente ;
	private DoctorDto idDoctor ;
	private String fechaCreacion ;

}
