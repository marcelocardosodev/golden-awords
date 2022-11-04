package com.filme.goldenawords.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IntervaloPremioDto {

	@JsonProperty("min")
	private List<ResultadoCompiladoDto> min;

	@JsonProperty("max")
	private List<ResultadoCompiladoDto> max;

}
