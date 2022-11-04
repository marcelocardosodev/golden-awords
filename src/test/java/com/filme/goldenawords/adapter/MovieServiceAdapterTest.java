package com.filme.goldenawords.adapter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filme.goldenawords.MockMovie;
import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.repository.MovieRepository;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class MovieServiceAdapterTest {
	
	
	@InjectMocks
	MovieServiceAdapter movieServiceAdapter;
	
	@Mock
	MovieRepository movieRepository;
	
	final MockMovie mockMovie = MockMovie.mockMovie();
	
	
	@BeforeEach
	public void init() {
		
		List<Movie> movies = mockMovie.getMovies();
		
		Mockito.lenient().when(movieRepository.getMoviePremiadoByProductors())
		.thenReturn(movies);
		
	}
	
	
	@Test
	public void getMoviePremiadoByProductorsTest() {
		
		List<Movie> esperado = mockMovie.getMovies();
		
		var listaPremiados = movieServiceAdapter.getMoviesProdutorsPremiados();
		
		Assertions.assertEquals(listaPremiados, esperado);
		
		
	}
	
	
	

}
