package com.digitalharbor.eval.rest.ui.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioDto extends DefaultDto {
	
	private static final long serialVersionUID = 1L;

	private String publicId ;
	
	private String username ;
	
	private String password ;
	
	private String fechaCreacion ;

}
