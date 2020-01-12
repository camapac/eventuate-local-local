package com.eventuate.example.ehistorical.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.eventuate.example.constant.TransactionAction;
import com.eventuate.example.constant.TransactionState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionView {

	@Id
	private String id;
	
	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	private String reason;
	private Date inventoryReservedDate;
	private Date paymentResultDate;
	private TransactionState preState;
	private Date createdDate = new Date(System.currentTimeMillis());
	private TransactionState state = TransactionState.INIT;
	private TransactionAction action;
}
