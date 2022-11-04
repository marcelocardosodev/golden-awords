package com.filme.goldenawords.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.port.MovieServicePort;
import com.filme.goldenawords.repository.MovieRepository;

import lombok.var;

@Service
public class MovieServiceAdapter implements MovieServicePort {

	@Autowired
	private MovieRepository repositoriy;
	
	@Override
	public List<Movie> getMoviesProdutorsPremiados() {
		
		var movies = repositoriy.getMoviePremiadoByProductors();
		return movies;
	}

}
