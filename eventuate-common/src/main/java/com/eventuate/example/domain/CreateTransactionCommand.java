package com.eventuate.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateTransactionCommand implements TransactionCommand {

	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
}
