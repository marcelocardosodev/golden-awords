package com.filme.goldenawords;

import java.util.ArrayList;
import java.util.List;

import com.filme.goldenawords.model.Producer;

import lombok.Getter;

@Getter
public class MockProducer {

	private MockProducer() {}
	
	private List<Producer> producers;
	
	
	public static MockProducer mockProducer() {
		
		List<Producer> mockProducers = new ArrayList<Producer>();
		mockProducers.add(Producer.builder().producerId(1)
				.name("Producer Teste 01").build());
		
		mockProducers.add(Producer.builder().producerId(2)
				.name("Producer Teste 02").build());
		
		mockProducers.add(Producer.builder().producerId(3)
				.name("Producer Teste 03").build());
		
		MockProducer mock = new MockProducer();
		
		mock.producers = mockProducers;
		
		
		return mock;
		
	}
}
