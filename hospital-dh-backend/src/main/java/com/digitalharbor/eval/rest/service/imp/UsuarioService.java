package com.digitalharbor.eval.rest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalharbor.eval.rest.entity.UsuarioEntity;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.repository.UsuarioRepository;
import com.digitalharbor.eval.rest.service.IUsuarioService;
import com.digitalharbor.eval.rest.ui.model.dto.UsuarioDto;
import com.digitalharbor.eval.rest.util.DateUtils;
import com.digitalharbor.eval.rest.util.Utils;

@Service
public class UsuarioService implements IUsuarioService<UsuarioDto> {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Utils utils = new Utils();

	@Override
	public UsuarioDto create(UsuarioDto dto) throws HospitalException {

		if (isValid(dto)) {

			Optional<UsuarioEntity> opt = Optional.ofNullable(repository.findByUsername(dto.getUsername()));
			if (opt.isPresent())
				throw new HospitalException(String.format("El nombre de usuario <%s> ya existe.", dto.getUsername()));

			UsuarioEntity entity = new UsuarioEntity();

			// generamos un ID de longitud 16
			entity.setPublicId(utils.generateUserId(16));

			entity.setUsername(dto.getUsername().toLowerCase());
			entity.setPassword(encoder.encode(dto.getPassword()));
			entity.setFechaCreacion(DateUtils.currentDate());

			entity = repository.save(entity);

			UsuarioDto dtoResponse = new UsuarioDto();
			BeanUtils.copyProperties(entity, dtoResponse);

			return dtoResponse;

		}

		return null;
	}

	@Override
	public UsuarioDto getUsuarioByUsername(String username) throws HospitalException {

		UsuarioEntity fromDb = repository.findByUsername(username);

		Optional<UsuarioEntity> opt = Optional.ofNullable(fromDb);
		if (!opt.isPresent()) {
			throw new HospitalException(String.format("El usuario %s no existe", username));
		}

		UsuarioDto dto = new UsuarioDto();
		dto.setFechaCreacion(DateUtils.convert(fromDb.getFechaCreacion()));
		dto.setUsername(fromDb.getUsername());
		dto.setPublicId(fromDb.getPublicId());

		return dto;
	}

	@Override
	public boolean isValid(UsuarioDto dto) throws HospitalException {

		if (dto.getUsername() == null || dto.getUsername().trim().length() == 0) {
			throw new HospitalException("Usuario es requerido");
		}

		if (dto.getPassword() == null || dto.getPassword().trim().length() == 0) {
			throw new HospitalException("Contrasena es requerido");
		}

		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsuarioEntity fromDb = repository.findByUsername(username);
		if (fromDb == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(fromDb.getUsername(), fromDb.getPassword(), new ArrayList<>());
	}

	@Override
	public UsuarioDto getUsuarioByPublicId(String publicId) throws HospitalException {
		UsuarioEntity fromDb = repository.findByUsername(publicId);

		Optional<UsuarioEntity> opt = Optional.ofNullable(fromDb);
		if (!opt.isPresent()) {
			throw new HospitalException("El usuario  no existe" );
		}

		UsuarioDto dto = new UsuarioDto();
		
		dto.setFechaCreacion(DateUtils.convert(fromDb.getFechaCreacion()));
		dto.setUsername(fromDb.getUsername());
		dto.setPublicId(fromDb.getPublicId());

		return dto;
	}

	@Override
	public UsuarioDto update(UsuarioDto dto) throws HospitalException {
		throw new NotYetImplementedException();
	}

	@Override
	public boolean delete(UsuarioDto dto) throws HospitalException {
		throw new NotYetImplementedException();
	}

	@Override
	public List<UsuarioDto> get(Integer id) throws HospitalException {
		throw new NotYetImplementedException();
	}

	@Override
	public List<UsuarioDto> get() throws HospitalException {
		throw new NotYetImplementedException();
	}

}
