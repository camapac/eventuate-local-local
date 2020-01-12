package com.eventuate.example.ehistorical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventuate.example.ehistorical.repository.TransactionViewRepository;
import com.eventuate.example.ehistorical.service.ITransactionHistoricalService;

public class TransactionHistoricalServiceImpl implements ITransactionHistoricalService {

	private TransactionViewRepository transactionViewRepository;

	@Autowired
	public TransactionHistoricalServiceImpl(TransactionViewRepository transactionViewRepository) {
		super();
		this.transactionViewRepository = transactionViewRepository;
	}
	

}
