package com.filme.goldenawords.port;

import java.util.List;

import com.filme.goldenawords.model.Movie;

public interface MovieServicePort {

	List<Movie> getMoviesProdutorsPremiados();

	List<Movie> getMoviesWinner();
}
