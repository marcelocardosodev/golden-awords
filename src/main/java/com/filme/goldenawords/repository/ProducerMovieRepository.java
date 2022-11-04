package com.filme.goldenawords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filme.goldenawords.model.ProducerMovie;

public interface ProducerMovieRepository extends JpaRepository<ProducerMovie, Integer> {

	@Query(
			value ="SELECT * FROM PRODUCER_MOVIE ORDER BY id DESC LIMIT 1", nativeQuery=true)
	ProducerMovie getMaxId();
}
