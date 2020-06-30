package com.digitalharbor.eval.rest.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.INotaService;
import com.digitalharbor.eval.rest.ui.model.dto.DoctorDto;
import com.digitalharbor.eval.rest.ui.model.dto.NotaDto;
import com.digitalharbor.eval.rest.ui.model.dto.PacienteDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class NotaServiceTest {
	@Autowired
	private INotaService<NotaDto> service ;

	@Test
	void test() {
		
		NotaDto dto = new NotaDto();
		
		dto.setDescripcion("Descripcion Medica");
		dto.setFechaAtencion("05/05/2020");
		dto.setPublicId("G1mm5Tnjs6hKz47l");
		
		DoctorDto d = new DoctorDto();
		d.setId(1);
		
		PacienteDto p = new PacienteDto();
		p.setId(2);
		
		dto.setIdPaciente(p);
		dto.setIdDoctor(d);
		
		
		NotaDto response ;
		try {
			response = service.create(dto);
			assertThat(response).isNotNull();
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
	}

}
