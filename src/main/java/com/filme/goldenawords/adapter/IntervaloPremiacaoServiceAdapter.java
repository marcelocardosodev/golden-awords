package com.filme.goldenawords.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.ProducerMovie;
import com.filme.goldenawords.model.dto.IntervaloPremioDto;
import com.filme.goldenawords.model.dto.ResultadoCompiladoDto;
import com.filme.goldenawords.port.IntevaloPremiacaoServicePort;

import lombok.var;


@Service
public class IntervaloPremiacaoServiceAdapter implements IntevaloPremiacaoServicePort {

	
	@Autowired MovieServiceAdapter movie;
	
	@Override
	public IntervaloPremioDto getIntevalo() {
		
		
		var movies = movie.getMoviesProdutorsPremiados();
		
		var listaPremiados = getPremios(movies);
		
		
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

	private List<ResultadoCompiladoDto> getPremios(List<Movie> movies) {
		List<ResultadoCompiladoDto> list = new ArrayList<ResultadoCompiladoDto>();
		Producer producer01 = new Producer();
		Producer producer02 = new Producer();
		Integer ano1 = 0;
		Integer ano2 = 0;
		for (Movie mov : movies) {

			for (ProducerMovie produ : mov.getProducersMovieList()) {
				producer01 = produ.getProducer();
				ano1 = Integer.parseInt(mov.getYear());
				for (Movie mov2 : movies) {
					for (ProducerMovie produ2 : mov2.getProducersMovieList()) {
						producer02 = produ2.getProducer();
						ano2 = Integer.parseInt(mov2.getYear());
						if (mov.getMovieId() != mov2.getMovieId() && producer01.equals(producer02)) {
							if (ano2 - ano1 > 0) {
								var item = ResultadoCompiladoDto.builder().producer(producer01.getName())
										.interval(ano2 - ano1)
										.followingWin(Integer.parseInt(mov2.getYear()))
										.previousWin(Integer.parseInt(mov.getYear())).build();
								list.add(item);
							}

						}
					}
					
				}
			}

		}

		return list;
	}

}
