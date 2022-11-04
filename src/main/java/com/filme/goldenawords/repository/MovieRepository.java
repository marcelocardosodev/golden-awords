package com.filme.goldenawords.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filme.goldenawords.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	public Movie findByTitle(String title);
	
	@Query(
			value ="SELECT * FROM MOVIE ORDER BY movie_id DESC LIMIT 1", nativeQuery=true)
	Movie getMaxId();
	
	@Query(
			value = "select * from movie  inner join producer_movie on movie.movie_id = producer_movie.movie_id "
					+ " inner join producer on producer_movie.producer_id = producer.producer_id "
					+ " where movie.winner = 'yes' and producer.producer_id in ( "
					+ " select producer.producer_id from movie  inner join producer_movie on movie.movie_id = producer_movie.movie_id "
					+ " inner join producer on producer_movie.producer_id = producer.producer_id "
					+ " where movie.winner = 'yes' Group by producer.producer_id having count(*) > 1 ) ", nativeQuery=true
			)
	List<Movie> getMoviePremiadoByProductors();

}
