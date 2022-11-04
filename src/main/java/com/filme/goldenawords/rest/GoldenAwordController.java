package com.filme.goldenawords.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filme.goldenawords.adapter.IntervaloPremiacaoServiceAdapter;
import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.dao.IntervaloPremioDto;
import com.filme.goldenawords.model.dao.ResultadoCompiladoDto;

import lombok.var;

@RestController
@RequestMapping(value="/")
public class GoldenAwordController {
	
	@Autowired
	private IntervaloPremiacaoServiceAdapter premiacaoService;
	
	@GetMapping
	public String  getTeste() {
		
		return "Hello word, teste";
	}
	
	
	@GetMapping("intervalo-premio")
	public ResponseEntity<IntervaloPremioDto> getIntevaloPremio(){
		
	
		return ResponseEntity.ok(premiacaoService.gerIntevalo());
	}
	

}
