package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.EspecialidadEntity;
import com.digitalharbor.eval.rest.entity.HospitalEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.EspecialidadRepository;
import com.digitalharbor.eval.rest.repository.HospitalRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IEspecialidadService;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class EspecialidadService implements IEspecialidadService<EspecialidadDto> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EspecialidadRepository repository;
	
	@Autowired
	private HospitalRepository hospitalRepository ;

	@Override
	public EspecialidadDto create(EspecialidadDto dto) throws HospitalException {

		if (isValid(dto)) {
			EspecialidadEntity entity = new EspecialidadEntity();
			BeanUtils.copyProperties(dto, entity);

			entity.setFechaCreacion(DateUtils.currentDate());

			entity.setCreadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));
			
			if (dto.getHospital()== null) {
				throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Hospital"));
			}
			
			/*Optional<HospitalEntity> hospital = hospitalRepository.findById(dto.getHospital().getId());
			if (!hospital.isPresent()) {
				throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_NO_EXISTE, "Hospital"));
			}
			
			entity.setHospitalId(hospital.get());*/
			
			HospitalEntity hospital = new HospitalEntity();
			hospital.setId(dto.getHospital().getId());
			
			entity.setHospitalId(hospital);
			
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
			
			Optional<HospitalEntity> hospital = hospitalRepository.findById(dto.getHospital().getId());
			if (!hospital.isPresent()) {
				throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_NO_EXISTE, "Hospital"));
			}

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
		response.setFechaCreacion(DateUtils.convert(fromDb.get().getFechaCreacion()));

		ArrayList<EspecialidadDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<EspecialidadDto> getByHospital(Integer id) throws HospitalException {
		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		HospitalEntity hospital = new HospitalEntity();
		hospital.setId(id);
		
		List<EspecialidadEntity> fromDb = repository.findByHospitalId(hospital);

		List<EspecialidadDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			EspecialidadDto fromBd = new EspecialidadDto();

			fromBd.setNombre(e.getNombre());
			fromBd.setDescripcion(e.getDescripcion());
			fromBd.setAvatar(e.getAvatar());
			fromBd.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));
			fromBd.setId(e.getId());
			
			HospitalDto h = new HospitalDto();
			h.setId(e.getHospitalId().getId());
			h.setNombre(e.getHospitalId().getNombre());

			fromBd.setHospital(h);

			response.add(fromBd);
		});

		return response;
	}

	@Override
	public List<EspecialidadDto> get() throws HospitalException {
		Iterable<EspecialidadEntity> fromDb = repository.findAll();

		List<EspecialidadDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			EspecialidadDto toResponse = new EspecialidadDto();

			toResponse.setNombre(e.getNombre());
			toResponse.setDescripcion(e.getDescripcion());
			toResponse.setAvatar(e.getAvatar());
			toResponse.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));

			response.add(toResponse);
		});

		return response;
	}

	/**
	 * 
	 * @param especialidad
	 * @return
	 * @throws HospitalException
	 */
	@Override
	public boolean isValid(EspecialidadDto especialidad) throws HospitalException {

		if (especialidad.getNombre() == null || especialidad.getNombre().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Nombre"));
		}

		return true;
	}

}
