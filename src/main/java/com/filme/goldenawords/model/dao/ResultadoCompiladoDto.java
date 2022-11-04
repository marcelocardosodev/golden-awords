package com.filme.goldenawords.model.dao;

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
public class ResultadoCompiladoDto {
	
	@JsonProperty("producer")
	private String producer;
	
	
	@JsonProperty("interval")
	private Integer interval;
	
	
	@JsonProperty("previousWin")
	private Integer previousWin;
	
	@JsonProperty("followingWin")
	private Integer followingWin;
	

}


