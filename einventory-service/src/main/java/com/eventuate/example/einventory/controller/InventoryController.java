package com.eventuate.example.einventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/inventory")
@RestController
public class InventoryController {

	
	@GetMapping
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok("Hello I'm Inventory!...");
	}
	
}
