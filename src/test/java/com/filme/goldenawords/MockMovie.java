package com.filme.goldenawords;

import java.util.ArrayList;
import java.util.List;

import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.Studio;
import com.filme.goldenawords.model.StudioMovie;

import lombok.Getter;
import lombok.var;

@Getter
public class MockMovie {
	
	private MockMovie() {}
	
	private List<Movie> movies;
	
	
	public static MockMovie mockMovie() {
		
		List<Movie> mockMovies = new ArrayList<Movie>();
		mockMovies.add(getMovie());
		
		
		MockMovie mock = new MockMovie();
		mock.movies = mockMovies;
		
		return mock;
	}


	private static Movie getMovie() {

		Movie movie =  Movie.builder()
				.movieId(1)
				.title("Titulo Teste")
				.year("2022")
				.winner("yes")
				.producersMovieList(getProducerMovie())
				.studiosMovieList(getStudioMovie())
				.build();
		
		return movie;
	}


	private static List<StudioMovie> getStudioMovie() {
		
		StudioMovie stMovie = StudioMovie.builder()
				.id(1)
				.movie(getSimpleMovie())
				.studio(getSimpleStudio()).build();
		
		
		List<StudioMovie> list =  new ArrayList<StudioMovie>();
		
		list.add(stMovie);
		
		return list;
	}


	private static String getProducerMovie() {
		var producers = "Producer Teste 01,Producer Teste 02,Producer Teste03";
		return producers;
	}
	
	
	private static Movie getSimpleMovie() {
		
		Movie movie =  Movie.builder()
				.movieId(1)
				.title("Titulo Teste")
				.year("2022")
				.winner("yes").build();
		
		
		return movie;
	}
	
	
	private static Studio getSimpleStudio() {
		
		Studio studio = Studio.builder()
				    .studioId(1)
				    .name("Studio name teste")
				    .build();
		
		return studio;
	}
	
}
