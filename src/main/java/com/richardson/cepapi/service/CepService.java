package com.richardson.cepapi.service;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richardson.cepapi.dto.CepDTO;

@Service
public class CepService {

	public CepDTO getCep(String cep) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		URL url = new URL("http://viacep.com.br/ws/" + cep + "/json/");
		return mapper.readValue(url, CepDTO.class);
	}

}
