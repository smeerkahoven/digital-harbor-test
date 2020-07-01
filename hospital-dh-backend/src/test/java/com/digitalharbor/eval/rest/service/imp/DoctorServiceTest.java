package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IDoctorService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class DoctorServiceTest {

	@Autowired
	private IDoctorService<DoctorDto> service ;
	
	@Test
	void test() {

		
		DoctorDto dto = new DoctorDto();
		dto.setApellido("Coronado");
		dto.setNombre("Jose");
		dto.setDireccion("Av. Pirai");
		dto.setFechaNacimiento("10/09/1980");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		
		HospitalDto h = new HospitalDto();
		h.setId(2);
		
		dto.setHospital(h);
		
		EspecialidadDto esdto = new EspecialidadDto();
		esdto.setId(5);
		
		EspecialidadDto esdto2 = new EspecialidadDto();
		esdto2.setId(6);
		
		List<EspecialidadDto> l = new ArrayList<EspecialidadDto>();
		l.add(esdto);
		l.add(esdto2);
		
		dto.setEspecialidades(l);
		
		DoctorDto response;
		try {
			response = service.create(dto);
			
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
		/////////
		
		esdto = new EspecialidadDto();
		esdto.setId(1);
		
		dto = new DoctorDto();
		dto.setApellido("Pereira");
		dto.setNombre("Franco");
		dto.setDireccion("Calle La Salle");
		dto.setFechaNacimiento("07/08/1975");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		
		h = new HospitalDto();
		h.setId(5);
		
		dto.setHospital(h);
		
		
		l = new ArrayList<EspecialidadDto>();
		l.add(esdto);
		dto.setEspecialidades(l);
		
		try {
			response = service.create(dto);
			
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//@Test
	void testSearchByName() {

		DoctorDto dto = new DoctorDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		dto.setNombre("Juan");
		dto.setPublicId(StaticValues.PUBLIC_ID);

		try {

			List<DoctorDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	//@Test
	void testSearchByFechaNac() {

		DoctorDto dto = new DoctorDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		// dto.setNombre("Marcelo");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		dto.setFechaNacimiento("10/10/1960");

		try {

			List<DoctorDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	//@Test
	void testSearchByFechaApellido() {

		DoctorDto dto = new DoctorDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		// dto.setNombre("Marcelo");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		dto.setApellido("Maldonado");
		dto.setFechaNacimiento("01/01/2010");

		try {

			List<DoctorDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}

	}

}
