package com.eventuate.example.epayment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/payment")
@RestController
public class PaymentController {

	@GetMapping
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok("Hello I'm Inventory!...");
	}
}
