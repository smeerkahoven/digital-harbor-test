package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.IPacienteService;
import com.digitalharbor.eval.rest.ui.model.dto.HospitalDto;
import com.digitalharbor.eval.rest.ui.model.dto.PacienteDto;
import com.digitalharbor.eval.rest.ui.model.dto.UsuarioDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class PacienteServiceTest {

	@Autowired
	private IPacienteService<PacienteDto> service;

	//@Test
	void testCreate() {

		PacienteDto dto = new PacienteDto();

		dto.setApellido("Lanza");
		dto.setDireccion("Av Heroes del Chaco");
		dto.setFechaNacimiento("10/09/1984");
		dto.setNombre("Jose");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		
		HospitalDto h = new HospitalDto();
		h.setId(1);
		
		dto.setHospital(h);

		try {

			PacienteDto response = service.create(dto);

			assertThat(response).isNotNull();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
		
		dto = new PacienteDto();

		dto.setApellido("Callau");
		dto.setDireccion("Av Centinela");
		dto.setFechaNacimiento("15/07/1980");
		dto.setNombre("Lucas");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		
		h = new HospitalDto();
		h.setId(2);
		
		dto.setHospital(h);

		try {

			PacienteDto response = service.create(dto);

			assertThat(response).isNotNull();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	void testFindByHospital() {
		
		try {

			List<PacienteDto> response = service.getByHospital(1);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
	}

	//@Test
	void testUpdate() {

		PacienteDto dto = new PacienteDto();

		dto.setId(2);
		dto.setApellido("Azurduy");
		dto.setDireccion("Av Heroes del Chaco");
		dto.setFechaNacimiento("01/01/2010");
		dto.setNombre("Marcelo");
		dto.setPublicId("G1mm5Tnjs6hKz47l");

		try {

			PacienteDto response = service.update(dto);

			assertThat(response).isNotNull();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	// @Test
	void testDelete() {

		PacienteDto dto = new PacienteDto();

		dto.setId(1);
		dto.setApellido("Azurduy");
		dto.setDireccion("Av Heroes del Chaco");
		dto.setFechaNacimiento("01/01/2010");
		dto.setNombre("Marcelo");
		dto.setPublicId("G1mm5Tnjs6hKz47l");

		try {

			boolean response = service.delete(dto);

			assertThat(response).isTrue();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSearchByName() {

		PacienteDto dto = new PacienteDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		dto.setNombre("Marcelo");
		dto.setPublicId(StaticValues.PUBLIC_ID);

		try {

			List<PacienteDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSearchByFechaNac() {

		PacienteDto dto = new PacienteDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		// dto.setNombre("Marcelo");
		dto.setPublicId(StaticValues.PUBLIC_ID);
		dto.setFechaNacimiento("10/09/1984");

		try {

			List<PacienteDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSearchByFechaApellido() {

		PacienteDto dto = new PacienteDto();

		// dto.setId(1);
		/*
		 * dto.setApellido("Azurduy"); dto.setDireccion("Av Heroes del Chaco");
		 * dto.setFechaNacimiento("01/01/2010");
		 */
		// dto.setNombre("Marcelo");
		dto.setPublicId("G1mm5Tnjs6hKz47l");
		dto.setApellido("Azurduy");
		dto.setFechaNacimiento("01/01/2010");

		try {

			List<PacienteDto> response = service.search(dto);

			assertThat(response).isNotEmpty();

		} catch (HospitalException e) {
			e.printStackTrace();
		}

	}

	@Test
	void testSearchByAll() {
		
		PacienteDto dto = new PacienteDto();
		
		//dto.setId(1);
		/*dto.setApellido("Azurduy");
		dto.setDireccion("Av Heroes del Chaco");
		dto.setFechaNacimiento("01/01/2010");*/
		//dto.setNombre("Marcelo");
		dto.setPublicId("G1mm5Tnjs6hKz47l");
		dto.setApellido("Azurduy");
		dto.setNombre("Marcelo");
		
		try {

			List<PacienteDto> response = service.search(dto);

			assertThat(response).isNotEmpty();
			
		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	void testSearchByNameFail() {
		
		PacienteDto dto = new PacienteDto();
		
		//dto.setId(1);
		/*dto.setApellido("Azurduy");
		dto.setDireccion("Av Heroes del Chaco");
		dto.setFechaNacimiento("01/01/2010");*/
		//dto.setNombre("Marcelo");
		//dto.setPublicId("G1mm5Tnjs6hKz47l");
		//dto.setApellido("Azurduy");
		dto.setNombre("Maria");
		
		try {

			List<PacienteDto> response = service.search(dto);

			assertThat(response).isEmpty();;
			
		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

}
