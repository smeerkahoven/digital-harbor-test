package com.digitalharbor.eval.rest.ui.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.* ;
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ModelResponse<T> {

	public static int RESPONSE_OK = 100 ;
	public static int RESPONSE_ERROR = 101 ;
	public static int RESPONSE_WARNING = 102 ;
	
	public static String MSG_REGISTRO_CREADO = "Registro Creado.";
	public static String MSG_REGISTRO_ACTUALIZADO = "Registro Actualizado.";
	public static String MSG_REGISTRO_ELIMINADO = "Registro Eliminado.";
	public static String MSG_CREDENCIALES_INCORRECTAS = "Credenciales Incorrectas.";
	public static String MSG_LISTA_VACIA= "No se encontraron registros.";
	
	public static String MSG_ERROR_EN_EL_SERVER = "Existio un error en la aplicacion.";
	
	private Integer codigo ;
	private String mensaje ;
	private List<T> items ;
	
}
