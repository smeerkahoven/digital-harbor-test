package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.DoctorEntity;
import com.digitalharbor.eval.rest.entity.NotaEntity;
import com.digitalharbor.eval.rest.entity.PacienteEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.DoctorRepository;
import com.digitalharbor.eval.rest.repository.NotaRepository;
import com.digitalharbor.eval.rest.repository.PacienteRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.INotaService;
import com.digitalharbor.eval.rest.ui.model.dto.NotaDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class NotaService implements INotaService<NotaDto> {

	@Autowired
	private NotaRepository repository;
	
	@Autowired
	private PacienteRepository pacienteRepository ;
	
	@Autowired
	private DoctorRepository doctorRepository ;
	
	@Autowired
	private UsuarioRepository usuarioRepository ;

	@Override
	public NotaDto create(NotaDto dto) throws HospitalException {
		if (isValid(dto)) {
			NotaEntity entity = new NotaEntity();
			BeanUtils.copyProperties(dto, entity);

			entity.setFechaAtencion(DateUtils.convert(dto.getFechaAtencion()));
			entity.setFechaCreacion(DateUtils.currentDate());
			entity.setCreadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));
			
			Optional<DoctorEntity> docFromBd = doctorRepository.findById(dto.getIdDoctor().getId());
			if (!docFromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_DOCTOR_NO_ESPECIFICADO);
			}
			
			Optional<PacienteEntity> pacFromBd = pacienteRepository.findById(dto.getIdPaciente().getId());
			if (!pacFromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_PACIENTE_NO_ESPECIFICADO) ;
			}

			entity.setDoctorId(docFromBd.get());
			entity.setPacienteId(pacFromBd.get());

			NotaEntity response = repository.save(entity);

			Optional<NotaEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				NotaDto responseValue = new NotaDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public NotaDto update(NotaDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		if (isValid(dto)) {

			Optional<NotaEntity> fromBd = repository.findById(dto.getId());

			if (!fromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ACTUALIZAR);
			}

			NotaEntity entity = fromBd.get();

			BeanUtils.copyProperties(dto, entity);

			entity.setFechaAtencion(DateUtils.convert(dto.getFechaAtencion()));
			entity.setFechaActualizacion(DateUtils.currentDate());
			entity.setActualizadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));
			
			Optional<DoctorEntity> docFromBd = doctorRepository.findById(dto.getIdDoctor().getId());
			if (!docFromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_DOCTOR_NO_ESPECIFICADO);
			}
			
			Optional<PacienteEntity> pacFromBd = pacienteRepository.findById(dto.getIdPaciente().getId());
			if (!pacFromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_PACIENTE_NO_ESPECIFICADO) ;
			}

			entity.setDoctorId(docFromBd.get());
			entity.setPacienteId(pacFromBd.get());

			NotaEntity response = repository.save(entity);

			Optional<NotaEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				NotaDto responseValue = new NotaDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}
		return null;
	}

	@Override
	public boolean delete(NotaDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<NotaEntity> fromBd = repository.findById(dto.getId());
		if (!fromBd.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ELIMINAR);
		}

		repository.deleteById(dto.getId());

		return true;
	}

	@Override
	public List<NotaDto> get(Integer id) throws HospitalException {
		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<NotaEntity> fromDb = repository.findById(id);

		if (!fromDb.isPresent()) {
			return new ArrayList<>();
		}

		NotaDto response = new NotaDto();
		BeanUtils.copyProperties(fromDb.get(), response);

		ArrayList<NotaDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<NotaDto> get() throws HospitalException {

		Iterable<NotaEntity> fromDb = repository.findAll() ;
		
		List<NotaDto> response = new ArrayList<>();
		
		fromDb.forEach(e ->  {
			NotaDto toResponse = new NotaDto();
			BeanUtils.copyProperties(e, toResponse);
			
			toResponse.setFechaAtencion(DateUtils.convert(e.getFechaAtencion()));
			
			response.add(toResponse);
		});
		
		return response ;
	}

	@Override
	public boolean isValid(NotaDto dto) throws HospitalException {
		
		if (dto.getDescripcion() ==null || dto.getDescripcion().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Descripcion"));
		}
		
		if (dto.getFechaAtencion() == null) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Fecha de Atencion"));
		}
		
		if (!DateUtils. esFechaValida(dto.getFechaAtencion())) {
			throw new HospitalException(ExceptionMessages.MSG_FORMATO_FECHA_DD_MM_YYYY);
		}
		
		if (dto.getIdDoctor() == null) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Doctor"));
		}
		
		if (dto.getIdPaciente() == null) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Paciente"));
		}
		
		return true;
	}

}
