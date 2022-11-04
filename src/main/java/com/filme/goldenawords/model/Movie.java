package com.filme.goldenawords.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1693147151153839802L;


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "movie_id")
	private Integer movieId;
	
	@Column(name = "year_movie")
	private String year;
	
	@Column(name = "title")
	private String title;
	
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<StudioMovie> studiosMovieList;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<ProducerMovie> producersMovieList;
	
	@Column(name = "winner")
	private String winner;
	

}
