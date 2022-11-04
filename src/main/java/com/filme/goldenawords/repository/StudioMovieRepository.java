package com.filme.goldenawords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filme.goldenawords.model.StudioMovie;

public interface StudioMovieRepository extends JpaRepository<StudioMovie, Integer> {

	@Query(
			value ="SELECT * FROM STUDIO_MOVIE ORDER BY id DESC LIMIT 1", nativeQuery=true)
	StudioMovie getMaxId();
}
