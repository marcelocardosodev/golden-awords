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

import com.filme.goldenawords.MockProducer;
import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.repository.ProducerRepository;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceAdapterTeste {
	
	@InjectMocks
	ProducerServiceAdapter producerAdapater;
	
	@Mock
	ProducerRepository repository;
	
	final MockProducer mockProducer = MockProducer.mockProducer();
	
	
	@BeforeEach
	public void init() {
		
		List<Producer> producers = mockProducer.getProducers();
		
		Mockito.lenient().when(repository.findAll())
		.thenReturn(producers);
		
	}
	
	
	@Test
	public void getMoviePremiadoByProductorsTest() {
		
		List<Producer> esperado  = mockProducer.getProducers();
		
		var listaPremiados = producerAdapater.getProducers();
		
		Assertions.assertEquals(listaPremiados, esperado);
		
		
	}

}
