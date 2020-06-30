package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IEspecialidadService;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class EspecialidadServiceTest {
	
	@Autowired
	private IEspecialidadService<EspecialidadDto> service ;

	@Test
	void testCreate() {
		
		EspecialidadDto dto = new EspecialidadDto();
		dto.setDescripcion("Otorrinolaringologia");
		dto.setNombre("Otorrinolaringologia");
		dto.setPublicId("G1mm5Tnjs6hKz47l");

		try {
			EspecialidadDto response = service.create(dto);
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			
			e.printStackTrace();
		}
		
	}

}
