/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalharbor.eval.rest.ui.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IUsuarioService;
import com.digitalharbor.eval.rest.ui.model.dto.UsuarioDto;
import com.digitalharbor.eval.rest.ui.model.request.UsuarioRequest;
import com.digitalharbor.eval.rest.ui.model.response.ModelResponse;
import com.digitalharbor.eval.rest.ui.model.response.UsuarioResponse;

/**
 *
 * @author CHEYO
 */

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
	@Autowired
	private IUsuarioService<UsuarioDto> service ;

	@PostMapping
	public UsuarioResponse create(@RequestBody UsuarioRequest request) {
		
		UsuarioResponse response = new UsuarioResponse();
		
		UsuarioDto dto ;
		
		try {
			dto = service.create(request);
			Optional<UsuarioDto> opt = Optional.ofNullable(dto);
			
			if (opt.isPresent()) {
				response.setCodigo(ModelResponse.RESPONSE_OK);
				response.setMensaje(ModelResponse.MSG_REGISTRO_CREADO);
			}
			
			
		} catch (HospitalException e) {
			response.setCodigo(ModelResponse.RESPONSE_ERROR);
			response.setMensaje(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			response.setCodigo(ModelResponse.RESPONSE_ERROR);
			response.setMensaje(ModelResponse.MSG_ERROR_EN_EL_SERVER);
			e.printStackTrace();
		}
		
		return response ;
		
	}
	
	
	
}
