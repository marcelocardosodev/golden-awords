package com.filme.goldenawords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.ProducerMovie;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {

	Producer findByName(String string);
	
	@Query(
			value ="SELECT * FROM PRODUCER ORDER BY producer_id DESC LIMIT 1", nativeQuery=true)
	Producer getMaxId();


}
