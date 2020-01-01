package com.eventuate.example.entity;

import com.eventuate.example.domain.TransactionCommand;

import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction extends ReflectiveMutableCommandProcessingAggregate<Transaction, TransactionCommand> {

	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	
}
