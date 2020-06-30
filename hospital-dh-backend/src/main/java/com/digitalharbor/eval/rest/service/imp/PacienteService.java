package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.DoctorEntity;
import com.digitalharbor.eval.rest.entity.PacienteEntity;
import com.digitalharbor.eval.rest.entity.UsuarioEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.PacienteRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IPacienteService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.ui.model.dto.PacienteDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class PacienteService implements IPacienteService<PacienteDto> {
	
	@PersistenceContext
	private EntityManager em ;

	@Autowired
	private PacienteRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public PacienteDto create(PacienteDto paciente) throws HospitalException {

		if (isValid(paciente)) {
			PacienteEntity entity = new PacienteEntity();
			BeanUtils.copyProperties(paciente, entity);

			entity.setFechaNacimiento(DateUtils.convert(paciente.getFechaNacimiento()));
			entity.setFechaCreacion(DateUtils.currentDate());

			//
			entity.setCreadoPor(usuarioRepository.findByPublicId(paciente.getPublicId()));
			//

			PacienteEntity response = repository.save(entity);

			Optional<PacienteEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				PacienteDto responseValue = new PacienteDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	/**
	 * Valida si los datos enviados son correctos
	 * 
	 * @param paciente
	 * @return
	 * @throws HospitalException
	 */
	@Override
	public boolean isValid(PacienteDto paciente) throws HospitalException {
		if (paciente.getNombre() == null || paciente.getNombre().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Nombre"));
		}

		if (paciente.getApellido() == null || paciente.getApellido().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Apellido"));
		}

		/*
		 * if (paciente.getFechaNacimiento() == null ) { throw new
		 * HospitalException("Fecha de Navimiento es requerido"); }
		 */

		if (!DateUtils.esFechaValida(paciente.getFechaNacimiento())) {
			throw new HospitalException(ExceptionMessages.MSG_FORMATO_FECHA_DD_MM_YYYY);
		}

		if (paciente.getDireccion() == null || paciente.getDireccion().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Direccion"));
		}

		return true;
	}

	@Override
	public PacienteDto update(final PacienteDto paciente) throws HospitalException {
		if (paciente.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		if (isValid(paciente)) {

			Optional<PacienteEntity> fromBd = repository.findById(paciente.getId());

			if (!fromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ACTUALIZAR);
			}

			PacienteEntity entity = fromBd.get();

			BeanUtils.copyProperties(paciente, entity);

			entity.setFechaNacimiento(DateUtils.convert(paciente.getFechaNacimiento()));
			entity.setFechaActualizacion(DateUtils.currentDate());

			// begin usuario actualizar
			entity.setActualizadoPor(usuarioRepository.findByPublicId(paciente.getPublicId()));
			//end usuario actualizar

			PacienteEntity response = repository.save(entity);

			Optional<PacienteEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				PacienteDto responseValue = new PacienteDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return paciente;
			}
		}

		return null;
	}

	@Override
	public boolean delete(PacienteDto paciente) throws HospitalException {
		if (paciente.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<PacienteEntity> fromBd = repository.findById(paciente.getId());
		if (!fromBd.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ELIMINAR);
		}

		repository.deleteById(paciente.getId());

		return true;
	}

	@Override
	public List<PacienteDto> get(Integer id) throws HospitalException {

		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<PacienteEntity> fromDb = repository.findById(id);

		if (!fromDb.isPresent()) {
			return new ArrayList<>();
		}

		PacienteDto response = new PacienteDto();
		BeanUtils.copyProperties(fromDb.get(), response);

		ArrayList<PacienteDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<PacienteDto> get() throws HospitalException {

		Iterable<PacienteEntity> fromDb = repository.findAll();

		List<PacienteDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			PacienteDto toResponse = new PacienteDto();
			BeanUtils.copyProperties(e, toResponse);
			toResponse.setFechaNacimiento(DateUtils.convert(e.getFechaNacimiento()));
			response.add(toResponse);
		});

		return response;
	}
	
	@Override
	public List<PacienteDto> search(PacienteDto dto) throws HospitalException {

		String query = "SELECT h.* FROM paciente h WHERE ";

		if (dto.getFechaNacimiento() == null && (dto.getNombre() == null || dto.getNombre().trim().length() == 0)
				&& (dto.getApellido() == null || dto.getApellido().trim().length() == 0)
				) {
			throw new HospitalException(ExceptionMessages.MSG_VALOR_BUSQUEDA);
		}

		if (dto.getFechaNacimiento() != null) {
			if(DateUtils.esFechaValida(dto.getFechaNacimiento())) {
				query += " h.fecha_nacimiento=:fechaNacimiento AND ";
			}
		}

		if (dto.getNombre() != null) {
			if (dto.getNombre().trim().length() > 0) {
				query += " h.nombre LIKE CONCAT('%',:nombre,'%') AND ";
			}
		}
		
		if (dto.getApellido()!= null) {
			if (dto.getApellido().trim().length() > 0) {
				query += " h.apellido LIKE CONCAT('%',:apellido,'%') AND ";
			}
		}
		
		query += " 1=1 ";
		
		Query q = em.createNativeQuery(query, PacienteEntity.class);
		
		if (dto.getFechaNacimiento() != null) {
			if(DateUtils.esFechaValida(dto.getFechaNacimiento())) {
				q.setParameter("fechaNacimiento", DateUtils.convert(dto.getFechaNacimiento()));
			}
		}

		if (dto.getNombre() != null) {
			if (dto.getNombre().trim().length() > 0) {
				q.setParameter("nombre", dto.getNombre());
			}
		}
		
		if (dto.getApellido() != null) {
			if (dto.getApellido().trim().length() > 0) {
				q.setParameter("apellido", dto.getApellido());
			}
		}


		List<PacienteEntity> list = q.getResultList() ;
		
		List<PacienteDto> responseList = new  ArrayList<>();
		
		list.forEach(e-> {
			PacienteDto fromBd = new PacienteDto();
			
			fromBd.setApellido(e.getApellido());
			fromBd.setDireccion(e.getDireccion());
			fromBd.setFechaNacimiento(DateUtils.convert(e.getFechaNacimiento()));
			fromBd.setFotoPerfil(e.getFotoPerfil());
			fromBd.setId(e.getId());
			fromBd.setNombre(e.getNombre());

			responseList.add(fromBd);
		});
		
		return responseList;
	}

}
