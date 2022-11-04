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
public class Producer implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3920962665303223869L;
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer producerId;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
	private List<ProducerMovie> listProducerMovie;

}
