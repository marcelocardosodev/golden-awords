package com.filme.goldenawords;

import java.util.ArrayList;
import java.util.List;

import com.filme.goldenawords.model.dto.IntervaloPremioDto;
import com.filme.goldenawords.model.dto.ResultadoCompiladoDto;

import lombok.Getter;
import lombok.var;

@Getter
public class MockIntervaloPremioDto {
	
	private MockIntervaloPremioDto() {}
	
	private IntervaloPremioDto intervalorPremio;
	
	
	public static MockIntervaloPremioDto mockIntervaloPremioDto() {
		
		IntervaloPremioDto intervalo = IntervaloPremioDto.builder()
				.min(getResultadoCompiladoMinimo())
				.max(getResultadoCompiladoMaximo())
				.build();
		
		MockIntervaloPremioDto mock = new MockIntervaloPremioDto();
		
		mock.intervalorPremio = intervalo;
		
		return mock;
	}


	private static List<ResultadoCompiladoDto> getResultadoCompiladoMaximo() {
		List<ResultadoCompiladoDto> list = new  ArrayList<ResultadoCompiladoDto>();
		var complicadoMaximo = ResultadoCompiladoDto .builder()
				.interval(22)
				.producer("Nome Producer Teste")
				.previousWin(2000)
				.followingWin(2022).build();
		
		list.add(complicadoMaximo);
		
		
		
		return list;
	}


	private static List<ResultadoCompiladoDto> getResultadoCompiladoMinimo() {
		List<ResultadoCompiladoDto> list = new  ArrayList<ResultadoCompiladoDto>();
		var complicadoMinimo = ResultadoCompiladoDto .builder()
				.interval(2)
				.producer("Nome Producer Teste")
				.previousWin(2020)
				.followingWin(2022).build();
		
		list.add(complicadoMinimo);
		
		
		
		return list;
	}
	
	
	
	

}
