package com.digitalharbor.eval.rest.ui.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioResponse extends ModelResponse {
	
	private String token ;
	private String username;
	private String idUsuario;
	private String publicId ;

}
