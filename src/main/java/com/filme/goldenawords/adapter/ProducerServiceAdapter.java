package com.filme.goldenawords.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.port.ProducerServicePort;
import com.filme.goldenawords.repository.ProducerRepository;

import lombok.var;

@Service
public class ProducerServiceAdapter implements ProducerServicePort {

	
	@Autowired
	ProducerRepository repository;
	
	@Override
	public List<Producer> getProducers() {
		
		var listProducer = repository.findAll();
		return listProducer;
	}

}
