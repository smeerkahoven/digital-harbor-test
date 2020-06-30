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
import com.digitalharbor.eval.rest.service.IHospitalService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.ui.model.request.DoctorRequest;
import com.digitalharbor.eval.rest.ui.model.request.HospitalRequest;
import com.digitalharbor.eval.rest.ui.model.response.DoctorResponse;
import com.digitalharbor.eval.rest.ui.model.response.HospitalResponse;
import com.digitalharbor.eval.rest.ui.model.response.ModelResponse;

/**
 * 
 * @author CHEYO
 *
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {
	
	@Autowired
	private IHospitalService<HospitalDto> service ;
	
	/**
	 * Realiza la creacion de un registro de Paciente
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	public HospitalResponse create(@RequestHeader(name ="publicId",required =true) String publicId , 
			@RequestBody final HospitalRequest request) {

		HospitalResponse response = new HospitalResponse();

		HospitalDto dto;
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
	public HospitalResponse update(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody final HospitalRequest request) {
		HospitalResponse response = new HospitalResponse();

		HospitalDto dto;
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
	public HospitalResponse delete(@RequestBody final HospitalRequest request) {
		HospitalResponse response = new HospitalResponse();

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
	public HospitalResponse get(@PathVariable(value = "id") Integer id) {
		HospitalResponse response = new HospitalResponse();

		try {

			List<HospitalDto> list  = service.get(id);

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
	public HospitalResponse getAll() {
		HospitalResponse response = new HospitalResponse();

		try {

			List<HospitalDto> list = service.get();

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
	
	@PostMapping("buscar")
	public HospitalResponse buscar(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody final HospitalRequest request) {
		
		HospitalResponse response = new HospitalResponse();

		try {

			List<HospitalDto> list = service.search(request);

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
