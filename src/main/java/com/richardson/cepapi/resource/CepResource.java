package com.richardson.cepapi.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.richardson.cepapi.service.CepService;
import com.richardson.cepapi.dto.CepDTO;

@RestController
@RequestMapping("/consultacep")
public class CepResource {

	@Autowired
	private CepService service;

	@GetMapping("/{cep}")
	public ResponseEntity<CepDTO> getCep(@PathVariable String cep)
			throws JsonParseException, JsonMappingException, IOException {
		CepDTO dto = service.getCep(cep);
		return ResponseEntity.ok().body(dto);
	}

}
