package com.digitalharbor.eval.rest.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.digitalharbor.eval.rest.service.IDoctorService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.ui.model.dto.PacienteDto;
import com.digitalharbor.eval.rest.ui.model.request.DoctorRequest;
import com.digitalharbor.eval.rest.ui.model.request.HospitalRequest;
import com.digitalharbor.eval.rest.ui.model.request.PersonaRequest;
import com.digitalharbor.eval.rest.ui.model.response.DoctorResponse;
import com.digitalharbor.eval.rest.ui.model.response.HospitalResponse;
import com.digitalharbor.eval.rest.ui.model.response.ModelResponse;
import com.digitalharbor.eval.rest.ui.model.response.PacienteResponse;

/**
 * 
 * @author CHEYO
 *
 */

@RestController
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService<DoctorDto> service ;
	
	/**
	 * Realiza la creacion de un registro de Paciente
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	public DoctorResponse create(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody DoctorRequest request) {

		DoctorResponse response = new DoctorResponse();

		DoctorDto dto;
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
	@PostMapping("/update")
	public DoctorResponse update(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody final DoctorRequest request) {
		DoctorResponse response = new DoctorResponse();

		DoctorDto dto;
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

	@PostMapping("/delete")
	public DoctorResponse delete(@RequestBody final DoctorRequest request) {
		DoctorResponse response = new DoctorResponse();

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
	public DoctorResponse get(@PathVariable(value = "id") Integer id) {
		DoctorResponse response = new DoctorResponse();

		try {

			List<DoctorDto> list  = service.get(id);

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
	
	
	@GetMapping("hospital/{id}")
	public DoctorResponse getByHospital(@PathVariable(value = "id") Integer id) {
		DoctorResponse response = new DoctorResponse();

		try {

			List<DoctorDto> list  = service.getByHospital(id);

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
	public DoctorResponse getAll() {
		DoctorResponse response = new DoctorResponse();

		try {

			List<DoctorDto> list = service.get();

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
	
	
	@PostMapping("/search")
	public DoctorResponse buscar(@RequestHeader(name ="publicId",required =true) String publicId ,
			@RequestBody final DoctorRequest request) {
		
		DoctorResponse response = new DoctorResponse();

		try {

			List<DoctorDto> list = service.search(request);

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
