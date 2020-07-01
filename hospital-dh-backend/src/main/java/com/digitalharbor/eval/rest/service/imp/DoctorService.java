package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.DoctorEntity;
import com.digitalharbor.eval.rest.entity.EspecialidadEntity;
import com.digitalharbor.eval.rest.entity.HospitalEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.DoctorRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IDoctorService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class DoctorService implements IDoctorService<DoctorDto> {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DoctorRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public DoctorDto create(DoctorDto dto) throws HospitalException {

		if (isValid(dto)) {
			DoctorEntity entity = new DoctorEntity();
			BeanUtils.copyProperties(dto, entity);

			entity.setFechaNacimiento(DateUtils.convert(dto.getFechaNacimiento()));
			entity.setFechaCreacion(DateUtils.currentDate());

			entity.setCreadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			HospitalEntity hospitalId = new HospitalEntity();
			hospitalId.setId(dto.getHospital().getId());

			entity.setHospitalId(hospitalId);

			if (!dto.getEspecialidades().isEmpty()) {
				List<EspecialidadEntity> el = new ArrayList<>();
				dto.getEspecialidades().forEach(e -> {
					EspecialidadEntity etoDb = new EspecialidadEntity();
					
					etoDb.setId(e.getId());

					el.add(etoDb);
				});
				entity.setEspecialidades(el);
			}

			DoctorEntity response = repository.save(entity);

			Optional<DoctorEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				DoctorDto responseValue = new DoctorDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	/**
	 * Valida si los datoss enviados son correctos
	 * 
	 * @param paciente
	 * @return
	 * @throws HospitalException
	 */
	@Override
	public boolean isValid(DoctorDto dto) throws HospitalException {
		if (dto.getNombre() == null || dto.getNombre().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Nombre"));
		}

		if (dto.getApellido() == null || dto.getApellido().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Apellido"));
		}

		/*
		 * if (paciente.getFechaNacimiento() == null ) { throw new
		 * HospitalException("Fecha de Navimiento es requerido"); }
		 */

		if (!DateUtils.esFechaValida(dto.getFechaNacimiento())) {
			throw new HospitalException(ExceptionMessages.MSG_FORMATO_FECHA_DD_MM_YYYY);
		}

		if (dto.getDireccion() == null || dto.getDireccion().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_VALOR_REQUERIDO, "Direccion"));
		}

		return true;
	}

	@Override
	public DoctorDto update(final DoctorDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		if (isValid(dto)) {

			Optional<DoctorEntity> fromBd = repository.findById(dto.getId());

			if (!fromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ACTUALIZAR);
			}

			DoctorEntity entity = fromBd.get();

			BeanUtils.copyProperties(dto, entity);

			entity.setFechaNacimiento(DateUtils.convert(dto.getFechaNacimiento()));
			entity.setFechaActualizacion(DateUtils.currentDate());
			entity.setActualizadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			if (!entity.getEspecialidades().isEmpty()) {
				List<EspecialidadEntity> el = new ArrayList<EspecialidadEntity>();
				entity.getEspecialidades().forEach(e -> {
					EspecialidadEntity etoDb = new EspecialidadEntity();
					etoDb.setId(e.getId());

					el.add(etoDb);
				});
				entity.setEspecialidades(el);
			}

			DoctorEntity response = repository.save(entity);

			Optional<DoctorEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				DoctorDto responseValue = new DoctorDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public boolean delete(DoctorDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<DoctorEntity> fromBd = repository.findById(dto.getId());
		if (!fromBd.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ELIMINAR);
		}

		repository.deleteById(dto.getId());

		return true;
	}

	@Override
	public List<DoctorDto> get(Integer id) throws HospitalException {

		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<DoctorEntity> fromDb = repository.findById(id);

		if (!fromDb.isPresent()) {
			return new ArrayList<>();
		}

		DoctorDto response = new DoctorDto();
		BeanUtils.copyProperties(fromDb.get(), response);

		if (!fromDb.get().getEspecialidades().isEmpty()) {

			List<EspecialidadDto> especialidades = new ArrayList<>();

			fromDb.get().getEspecialidades().forEach(e -> {

				EspecialidadDto espFromDb = new EspecialidadDto();
				espFromDb.setDescripcion(e.getDescripcion());
				espFromDb.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));
				espFromDb.setId(e.getId());
				espFromDb.setNombre(e.getNombre());
				// TODO
				// espFromDb.setAvatar(e.getAvatar());
				especialidades.add(espFromDb);
			});
			
			response.setEspecialidades(especialidades);

		}

		ArrayList<DoctorDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<DoctorDto> getByHospital(Integer id) throws HospitalException {

		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		HospitalEntity hospital = new HospitalEntity();
		hospital.setId(id);
		
		List<DoctorEntity> fromDb = repository.findByHospitalId(hospital);

		
		List<DoctorDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			DoctorDto toResponse = new DoctorDto();
			BeanUtils.copyProperties(e, toResponse);
			toResponse.setFechaNacimiento(DateUtils.convert(e.getFechaNacimiento()));
			toResponse.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));

			HospitalDto h = new HospitalDto();
			h.setId(e.getHospitalId().getId());
			h.setNombre(e.getHospitalId().getNombre());

			toResponse.setHospital(h);

			response.add(toResponse);
		});

		return response;
	}

	@Override
	public List<DoctorDto> get() throws HospitalException {

		Iterable<DoctorEntity> fromDb = repository.findAll();

		List<DoctorDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			DoctorDto toResponse = new DoctorDto();
			BeanUtils.copyProperties(e, toResponse);
			toResponse.setFechaNacimiento(DateUtils.convert(e.getFechaNacimiento()));
			toResponse.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));

			HospitalDto hospital = new HospitalDto();
			hospital.setId(e.getHospitalId().getId());
			hospital.setNombre(e.getHospitalId().getNombre());

			toResponse.setHospital(hospital);

			response.add(toResponse);
		});

		return response;
	}

	@Override
	public List<DoctorDto> search(DoctorDto dto) throws HospitalException {

		String query = "SELECT h.* FROM doctor h WHERE ";

		if (dto.getFechaNacimiento() == null && (dto.getNombre() == null || dto.getNombre().trim().length() == 0)
				&& (dto.getApellido() == null || dto.getApellido().trim().length() == 0)) {
			throw new HospitalException(ExceptionMessages.MSG_VALOR_BUSQUEDA);
		}

		if (dto.getFechaNacimiento() != null || DateUtils.esFechaValida(dto.getFechaNacimiento())) {
				query += " h.fechaNacimiento=:fechaNacimiento AND ";
		}

		if (dto.getNombre() != null || dto.getNombre().trim().length() > 0) {
				query += " h.nombre LIKE CONCAT('%',:nombre,'%') AND ";
		}

		if (dto.getApellido() != null || dto.getApellido().trim().length() > 0) {
				query += " h.apellido LIKE CONCAT('%',:apellido,'%') AND ";
		}

		query += " 1=1 ";

		Query q = em.createQuery(query, DoctorEntity.class);

		if (dto.getFechaNacimiento() != null || DateUtils.esFechaValida(dto.getFechaNacimiento()) ) {
				q.setParameter("fechaNacimiento", DateUtils.convert(dto.getFechaNacimiento()));
			
		}

		if (dto.getNombre() != null  ||dto.getNombre().trim().length() > 0) {
				q.setParameter("nombre", dto.getNombre());
		}

		if (dto.getApellido() != null || (dto.getApellido().trim().length() > 0) ) {
				q.setParameter("apellido", dto.getApellido());
		}

		List<DoctorEntity> list = q.getResultList();

		List<DoctorDto> responseList = new ArrayList<>();

		list.forEach(e -> {
			DoctorDto fromBd = new DoctorDto();

			fromBd.setApellido(e.getApellido());
			fromBd.setDireccion(e.getDireccion());
			fromBd.setFechaNacimiento(DateUtils.convert(e.getFechaNacimiento()));
			fromBd.setFotoPerfil(e.getFotoPerfil());
			fromBd.setId(e.getId());
			fromBd.setNombre(e.getNombre());
			fromBd.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));
			
			HospitalDto hospital = new HospitalDto();
			hospital.setId(e.getHospitalId().getId());
			hospital.setNombre(e.getNombre());

			fromBd.setHospital(hospital);

			/*
			 * List<EspecialidadDto> listE = new ArrayList<>() ;
			 * 
			 * e.getEspecialidades().forEach(el -> { EspecialidadDto eFromDb = new
			 * EspecialidadDto();
			 * 
			 * eFromDb.setAvatar(el.getAvatar());
			 * eFromDb.setDescripcion(el.getDescripcion());
			 * eFromDb.setFechaCreacion(DateUtils.convert(el.getFechaCreacion()));
			 * eFromDb.setId(el.getId()); eFromDb.setNombre(el.getNombre());
			 * 
			 * listE.add(eFromDb); });
			 */

			responseList.add(fromBd);
		});

		return responseList;
	}

}
