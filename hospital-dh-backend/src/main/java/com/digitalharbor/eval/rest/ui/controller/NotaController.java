package com.digitalharbor.eval.rest.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.INotaService;
import com.digitalharbor.eval.rest.ui.model.dto.NotaDto;
import com.digitalharbor.eval.rest.ui.model.request.NotaRequest;
import com.digitalharbor.eval.rest.ui.model.response.ModelResponse;
import com.digitalharbor.eval.rest.ui.model.response.NotaResponse;

/**
 * 
 * @author CHEYO
 *
 */
@RestController
@RequestMapping("nota")
public class NotaController {
	
	@Autowired
	private INotaService<NotaDto> service ;
	
	/**
	 * Realiza la creacion de un registro de Paciente
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	public NotaResponse create(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody NotaRequest request) {

		NotaResponse response = new NotaResponse();

		NotaDto dto;
		try {
			request.setPublicId(publicId);
			
			dto =  service.create(request);
			Optional opt = Optional.ofNullable(dto);

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
		return response;
	}

	/**
	 * Actualizacion de Registro
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
	public NotaResponse update(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody final NotaRequest request) {
		NotaResponse response = new NotaResponse();

		NotaDto dto;
		try {
			request.setPublicId(publicId);
			
			dto = service.update(request);
			Optional opt = Optional.ofNullable(dto);

			if (opt.isPresent()) {
				response.setCodigo(ModelResponse.RESPONSE_OK);
				response.setMensaje(ModelResponse.MSG_REGISTRO_ACTUALIZADO);
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
		return response;
	}

	@DeleteMapping
	public NotaResponse delete(@RequestBody  NotaRequest request) {
		NotaResponse response = new NotaResponse();

		try {
			if (service.delete(request)) {
				response.setCodigo(ModelResponse.RESPONSE_OK);
				response.setMensaje(ModelResponse.MSG_REGISTRO_ELIMINADO);
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
		return response;
	}

	@GetMapping("{id}")
	public NotaResponse get(@PathVariable(value = "id") Integer id) {
		NotaResponse response = new NotaResponse();

		try {

			List<NotaDto> list  = service.get(id);

			if (list.isEmpty() ) {
				response.setCodigo(ModelResponse.RESPONSE_WARNING);
				response.setMensaje(ModelResponse.MSG_LISTA_VACIA);
			}else {
				response.setItems(list);
				response.setCodigo(ModelResponse.RESPONSE_OK);
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
		return response;
	}

	@GetMapping
	public NotaResponse getAll() {
		NotaResponse response = new NotaResponse();

		try {

			List<NotaDto> list = service.get();

			if (list.isEmpty()) {
				response.setCodigo(ModelResponse.RESPONSE_WARNING);
				response.setMensaje(ModelResponse.MSG_LISTA_VACIA);
				response.setItems(new ArrayList<>());
			} else {
				response.setItems(list);
				response.setCodigo(ModelResponse.RESPONSE_OK);
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
		return response;
	}
	
	

}
