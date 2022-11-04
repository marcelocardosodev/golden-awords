package com.filme.goldenawords.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Integer> {

	public Studio findByName(String name);
	
	@Query(
			value ="SELECT * FROM STUDIO ORDER BY studio_id DESC LIMIT 1", nativeQuery=true)
	Studio getMaxId();


}
