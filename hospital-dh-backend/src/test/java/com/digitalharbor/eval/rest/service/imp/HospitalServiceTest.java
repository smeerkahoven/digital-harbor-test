package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IHospitalService;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class HospitalServiceTest {
	@Autowired
	private IHospitalService<HospitalDto> service ;

	@Test
	void test() {
		HospitalDto dto = new HospitalDto();
		dto.setNombre("Hospital Un Paso a la Muerte");
		dto.setDireccion("2do Anillo.");
		dto.setPublicId(StaticValues.PUBLIC_ID);

		try {
			HospitalDto response = service.create(dto);
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			
			e.printStackTrace();
		}
		
		 dto = new HospitalDto();
		dto.setNombre("Hospital Un Paso a la Muerte");
		dto.setDireccion("2do Anillo.");
		dto.setPublicId(StaticValues.PUBLIC_ID);;

		try {
			HospitalDto response = service.create(dto);
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdate() {
		HospitalDto dto = new HospitalDto();
		dto.setId(1);
		dto.setNombre("Hospital Incor");
		dto.setDireccion("Av Los Andes");
		dto.setPublicId(StaticValues.PUBLIC_ID);

		try {
			HospitalDto response = service.create(dto);
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			
			e.printStackTrace();
		}
	}

}
