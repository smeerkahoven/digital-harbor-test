package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IUsuarioService;
import com.digitalharbor.eval.rest.ui.model.dto.UsuarioDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsuarioServiceTest {

	@Autowired
	private IUsuarioService<UsuarioDto> service;


	@Test
	void testCreate() {
		UsuarioDto dto = new UsuarioDto();
		dto.setUsername("josemiguel");
		dto.setPassword("12345");

		try {

			UsuarioDto response = service.create(dto);

			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			e.printStackTrace();
		}

	}

}
