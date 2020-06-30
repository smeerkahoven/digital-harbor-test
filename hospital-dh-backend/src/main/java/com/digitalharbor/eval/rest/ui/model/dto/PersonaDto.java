package com.digitalharbor.eval.rest.ui.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public abstract class PersonaDto extends DefaultDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id ; 
	private String nombre ;
	private String apellido ;
	private String fechaNacimiento ;
	private String direccion ;
	private byte[] fotoPerfil ;
	private String fechaCreacion ;
	private String fechaActualizacion ;

}
