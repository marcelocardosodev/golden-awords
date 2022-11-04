package com.filme.goldenawords.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filme.goldenawords.MockIntervaloPremioDto;
import com.filme.goldenawords.MockMovie;
import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.dto.IntervaloPremioDto;
import com.filme.goldenawords.model.dto.ResultadoCompiladoDto;
import com.filme.goldenawords.repository.MovieRepository;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class IntervaloPremiacaoServiceAdapterTest {
	
	
	@InjectMocks
	IntervaloPremiacaoServiceAdapter intervalPremiacaoService;
	
	@Mock
	MovieRepository movieRepository;
	
	@Mock
	MovieServiceAdapter moveAdapter;
	
	
	
	
	
	final MockMovie mockMovie = MockMovie.mockMovie();
	
	final MockIntervaloPremioDto mockIntevalo = MockIntervaloPremioDto.mockIntervaloPremioDto();
	
	
	@BeforeEach
	public void init() {
		
		List<Movie> movies = mockMovie.getMovies();
		Mockito.lenient().when(moveAdapter.getMoviesProdutorsPremiados())
		.thenReturn(movies);
		
		IntervaloPremioDto intevalo = mockIntevalo.getIntervalorPremio();
		
//		Mockito.lenient().when(intervalPremiacaoService.getIntevalo())
//		.thenReturn(intevalo);
		
		
		
	}
	
	@Test
	public void getIntevaloTestNotEquals() {
		
		IntervaloPremioDto intevalo = mockIntevalo.getIntervalorPremio();
		
		var retornoteste = intervalPremiacaoService.getIntevalo();
		
		Assertions.assertNotEquals(retornoteste, intevalo);
		
	}
	
	@Test
	public void getIntevaloTest() {
		
		List<ResultadoCompiladoDto> listTeste = new  ArrayList<ResultadoCompiladoDto>();
		IntervaloPremioDto intervalo = mockIntevalo.getIntervalorPremio();
		intervalo.setMax(listTeste);
		intervalo.setMin(listTeste);
		
		var retornoteste = intervalPremiacaoService.getIntevalo();
		
		Assertions.assertEquals(retornoteste.getMax(), intervalo.getMax());
		Assertions.assertEquals(retornoteste.getMin(), intervalo.getMin());
		
	}

}
