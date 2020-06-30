package com.digitalharbor.eval.rest.ui.model.response;

import com.digitalharbor.eval.rest.ui.model.dto.EspecialidadDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class EspecialidadResponse extends ModelResponse<EspecialidadDto> {
	

}
