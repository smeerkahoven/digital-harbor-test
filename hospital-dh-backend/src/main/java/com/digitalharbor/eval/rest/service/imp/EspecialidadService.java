package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.EspecialidadEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.EspecialidadRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IEspecialidadService;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class EspecialidadService implements IEspecialidadService<EspecialidadDto> {

	@Autowired
	private UsuarioRepository usuarioRepository ;
	
	@Autowired
	private EspecialidadRepository repository;

	@Override
	public EspecialidadDto create(EspecialidadDto dto) throws HospitalException {

		if (isValid(dto)) {
			EspecialidadEntity entity = new EspecialidadEntity();
			BeanUtils.copyProperties(dto, entity);

			entity.setFechaCreacion(DateUtils.currentDate());
			
			entity.setCreadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			EspecialidadEntity response = repository.save(entity);
			Optional<EspecialidadEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				EspecialidadDto responseValue = new EspecialidadDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public EspecialidadDto update(EspecialidadDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		if (isValid(dto)) {

			Optional<EspecialidadEntity> fromBd = repository.findById(dto.getId());

			if (!fromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ACTUALIZAR);
			}

			EspecialidadEntity entity = fromBd.get();

			entity.setNombre(dto.getNombre());
			entity.setDescripcion(dto.getDescripcion());
			entity.setAvatar(dto.getAvatar());
			entity.setFechaActualizacion(DateUtils.currentDate());
			
			entity.setActualizadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			EspecialidadEntity response = repository.save(entity);

			Optional<EspecialidadEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				EspecialidadDto responseValue = new EspecialidadDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public boolean delete(EspecialidadDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<EspecialidadEntity> fromBd = repository.findById(dto.getId());
		if (!fromBd.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ELIMINAR);
		}

		repository.deleteById(dto.getId());

		return true;
	}

	@Override
	public List<EspecialidadDto> get(Integer id) throws HospitalException {
		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<EspecialidadEntity> fromDb = repository.findById(id);

		if (!fromDb.isPresent()) {
			return new ArrayList<>();
		}

		EspecialidadDto response = new EspecialidadDto();
		BeanUtils.copyProperties(fromDb.get(), response);

		ArrayList<EspecialidadDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<EspecialidadDto> get() throws HospitalException {
		Iterable<EspecialidadEntity> fromDb = repository.findAll() ;
		
		List<EspecialidadDto> response = new ArrayList<>();
		
		fromDb.forEach(e ->  {
			EspecialidadDto toResponse = new EspecialidadDto();

			toResponse.setNombre(e.getNombre());
			toResponse.setDescripcion(e.getDescripcion());
			toResponse.setAvatar(e.getAvatar());
			
			response.add(toResponse);
		});
		
		return response ;
	}

	/**
	 * 
	 * @param especialidad
	 * @return
	 * @throws HospitalException
	 */
	@Override
	public boolean isValid( EspecialidadDto especialidad) throws HospitalException {

		if (especialidad.getNombre() == null || especialidad.getNombre().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Nombre"));
		}

		return true;
	}

}
