package com.richardson.cepapi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppResource {

	@GetMapping
	public ResponseEntity<Void> isOnline() {
		return ResponseEntity.ok().build();
	}

}
