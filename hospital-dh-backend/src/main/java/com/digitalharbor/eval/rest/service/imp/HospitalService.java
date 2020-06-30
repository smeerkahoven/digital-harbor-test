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

import com.digitalharbor.eval.rest.entity.HospitalEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.HospitalRepository;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IHospitalService;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.ExceptionMessages;

@Service
public class HospitalService implements IHospitalService<HospitalDto> {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UsuarioRepository usuarioRepository ;
	
	@Autowired
	private HospitalRepository repository;

	@Override
	public HospitalDto create(HospitalDto dto) throws HospitalException {
		if (isValid(dto)) {
			HospitalEntity entity = new HospitalEntity();
			BeanUtils.copyProperties(dto, entity);

			entity.setFechaCreacion(DateUtils.currentDate());
			entity.setCreadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			HospitalEntity response = repository.save(entity);

			Optional<HospitalEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				HospitalDto responseValue = new HospitalDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public HospitalDto update(HospitalDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		if (isValid(dto)) {

			Optional<HospitalEntity> fromBd = repository.findById(dto.getId());

			if (!fromBd.isPresent()) {
				throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ACTUALIZAR);
			}

			HospitalEntity entity = fromBd.get();

			BeanUtils.copyProperties(dto, entity);

			entity.setFechaActualizacion(DateUtils.currentDate());
			entity.setActualizadoPor(usuarioRepository.findByPublicId(dto.getPublicId()));

			HospitalEntity response = repository.save(entity);

			Optional<HospitalEntity> op = Optional.ofNullable(response);
			if (op.isPresent()) {
				HospitalDto responseValue = new HospitalDto();
				BeanUtils.copyProperties(op.get(), responseValue);
				return responseValue;
			}
		}

		return null;
	}

	@Override
	public boolean delete(HospitalDto dto) throws HospitalException {
		if (dto.getId() == null) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<HospitalEntity> fromBd = repository.findById(dto.getId());
		if (!fromBd.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_NO_EXISTE_REGISTRO_ELIMINAR);
		}

		repository.deleteById(dto.getId());

		return true;
	}

	@Override
	public List<HospitalDto> get(Integer id) throws HospitalException {
		Optional<Integer> idValid = Optional.ofNullable(id);

		if (!idValid.isPresent()) {
			throw new HospitalException(ExceptionMessages.MSG_INGRESE_ID_VALIDO);
		}

		Optional<HospitalEntity> fromDb = repository.findById(id);

		if (!fromDb.isPresent()) {
			return new ArrayList<>();
		}

		HospitalDto response = new HospitalDto();
		BeanUtils.copyProperties(fromDb.get(), response);

		ArrayList<HospitalDto> items = new ArrayList<>();
		items.add(response);

		return items;
	}

	@Override
	public List<HospitalDto> get() throws HospitalException {
		Iterable<HospitalEntity> fromDb = repository.findAll();

		List<HospitalDto> response = new ArrayList<>();

		fromDb.forEach(e -> {
			HospitalDto toResponse = new HospitalDto();
			BeanUtils.copyProperties(e, toResponse);
			response.add(toResponse);
		});

		return response;
	}

	@Override
	public boolean isValid(HospitalDto dto) throws HospitalException {

		if (dto.getNombre() == null || dto.getNombre().trim().length() == 0) {
			throw new HospitalException(String.format(ExceptionMessages.MSG_INGRESE_ID_VALIDO, "Nombre"));
		}

		return true;
	}

	@Override
	public List<HospitalDto> search(HospitalDto dto) throws HospitalException {

		String query = "SELECT h FROM HospitalEntity h WHERE ";

		if (dto.getFechaCreacion() == null && (dto.getNombre() == null || dto.getNombre().trim().length() == 0)) {
			throw new HospitalException(ExceptionMessages.MSG_VALOR_BUSQUEDA);
		}

		if (dto.getFechaCreacion() != null) {
			if(DateUtils.esFechaValida(dto.getFechaCreacion())) {
				query += " h.fechaCreacion=:fechaCrecion AND ";
			}
		}

		if (dto.getNombre() != null) {
			if (dto.getNombre().trim().length() > 0) {
				query += " h.nombre LIKE %:nombre% AND ";
			}
		}
		
		query += " AND 1=1 ";
		
		Query q = em.createQuery(query);
		
		if (dto.getFechaCreacion() != null) {
			if(DateUtils.esFechaValida(dto.getFechaCreacion())) {
				q.setParameter("fechaCreacion", DateUtils.convert(dto.getFechaCreacion()));
			}
		}

		if (dto.getNombre() != null) {
			if (dto.getNombre().trim().length() > 0) {
				q.setParameter("nombre", dto.getNombre());
			}
		}

				
		List<HospitalEntity> list = q.getResultList() ;
		
		List<HospitalDto> responseList = new  ArrayList<>();
		
		list.forEach(e-> {
			HospitalDto fromBd = new HospitalDto();
			fromBd.setDireccion(e.getDireccion());
			fromBd.setFechaCreacion(DateUtils.convert(e.getFechaCreacion()));
			fromBd.setId(e.getId());
			fromBd.setNombre(e.getNombre());
			
			responseList.add(fromBd);
		});
		
		return responseList;
	}

}
