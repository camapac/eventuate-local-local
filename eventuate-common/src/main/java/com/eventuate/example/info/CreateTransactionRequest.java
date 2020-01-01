package com.eventuate.example.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateTransactionRequest {

	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	
}
