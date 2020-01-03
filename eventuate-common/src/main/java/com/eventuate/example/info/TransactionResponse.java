package com.eventuate.example.info;

import com.eventuate.example.constant.TransactionState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionResponse {
	
	private String id;
	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	private TransactionState state;

}
