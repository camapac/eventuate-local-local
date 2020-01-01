package com.eventuate.example.etransaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transaction")
@RestController
public class ETransactionController {

	@GetMapping
	public ResponseEntity<String> testController(){
		return ResponseEntity.ok("Hello");
	}
}
