package com.filme.goldenawords.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.dto.IntervaloPremioDto;
import com.filme.goldenawords.model.dto.ResultadoCompiladoDto;
import com.filme.goldenawords.port.IntevaloPremiacaoServicePort;

import lombok.var;


@Service
public class IntervaloPremiacaoServiceAdapter implements IntevaloPremiacaoServicePort {

	
	@Autowired 
	MovieServiceAdapter movie;
	
	@Autowired
	ProducerServiceAdapter producer;
	
	@Override
	public IntervaloPremioDto getIntevalo() {
		
		
		var movies = movie.getMoviesWinner();
		
		var producers = producer.getProducers();
		
		var listaPremiados = getPremiosByProducer(movies,producers);
		
		var retorno = IntervaloPremioDto.builder().min(filterMenorIntevalo(listaPremiados)).max(filterMaiorIntevalo(listaPremiados)).build();
		
		return retorno;
		
	}

	private List<ResultadoCompiladoDto> filterMaiorIntevalo(List<ResultadoCompiladoDto> listaPremiados) {
		
		Integer inteval = Integer.MIN_VALUE;
		if(!listaPremiados.isEmpty()) {
			
			for (ResultadoCompiladoDto premiado : listaPremiados) {
				
				if(premiado.getInterval() > inteval) {
					inteval = premiado.getInterval();
				}
			}
		}
		
		final var indice = inteval;
		List<ResultadoCompiladoDto> results = listaPremiados.stream()
				.filter(result -> result.getInterval() == indice)
				.collect(Collectors.toList());
		return results;
	}

	private List<ResultadoCompiladoDto> filterMenorIntevalo(List<ResultadoCompiladoDto> listaPremiados) {
		Integer inteval = Integer.MAX_VALUE;
		if(!listaPremiados.isEmpty()) {
			
			for (ResultadoCompiladoDto premiado : listaPremiados) {
				
				if(premiado.getInterval() < inteval) {
					inteval = premiado.getInterval();
				}
			}
		}
		
		final var indice =  inteval;
		List<ResultadoCompiladoDto> results = listaPremiados.stream()
				.filter(result -> result.getInterval() == indice)
				.collect(Collectors.toList());
		return results;
	}

	private List<ResultadoCompiladoDto> getPremiosByProducer(List<Movie> movies, List<Producer> producers) {
		
		List<ResultadoCompiladoDto> list = new ArrayList<ResultadoCompiladoDto>();
		
		for (Producer prod : producers) {
			
			
			for(Movie movie1 : movies) {

				for(Movie movie2: movies) {
					
					if(movie1.getMovieId() != movie2.getMovieId() && contemProducer(prod.getName(), movie1.getProducersMovieList())
							&& contemProducer(prod.getName(), movie2.getProducersMovieList()) ) {
						
						if(Integer.parseInt(movie2.getYear()) - Integer.parseInt(movie1.getYear()) > 0) {
							
							var item = ResultadoCompiladoDto.builder()
									.previousWin(Integer.parseInt(movie1.getYear()))
									.followingWin(Integer.parseInt(movie2.getYear()))
									.producer(prod.getName())
									.interval(Integer.parseInt(movie2.getYear()) - Integer.parseInt(movie1.getYear()) ).build();
							list.add(item);
						}
								
					}
				}
			}
			
		}
		
		return list;
	}
	
	
	private Boolean contemProducer(String nomeProducer, String stringOfProducers) {
		
		String [] producers = stringOfProducers.split(",");
		
		List<String> listProducers = new ArrayList<String>();
		
		for (int i = 0; i < producers.length; i++) {
			String nameProducer = producers[i].replaceAll("^\\s+", "");
			nameProducer = nameProducer.trim();
			listProducers.add(nameProducer);
		}
		
		var resposta = listProducers.contains(nomeProducer);
		return resposta;
	}

}
