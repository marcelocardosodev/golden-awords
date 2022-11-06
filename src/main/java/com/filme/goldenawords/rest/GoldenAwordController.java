package com.filme.goldenawords.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filme.goldenawords.adapter.IntervaloPremiacaoServiceAdapter;
import com.filme.goldenawords.model.dto.IntervaloPremioDto;

@RestController
@RequestMapping(value="/")
public class GoldenAwordController {
	
	@Autowired
	private IntervaloPremiacaoServiceAdapter premiacaoService;
	
	
	@GetMapping("intervalo-premio")
	public ResponseEntity<IntervaloPremioDto> getIntevaloPremio(){
		
	
		return ResponseEntity.ok(premiacaoService.getIntevalo());
	}
	

}
