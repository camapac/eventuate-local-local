package com.eventuate.example.ehistorical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventuate.example.ehistorical.repository.TransactionViewRepository;
import com.eventuate.example.ehistorical.service.ITransactionHistoricalService;

@Service
public class TransactionHistoricalServiceImpl implements ITransactionHistoricalService {

	private TransactionViewRepository transactionViewRepository;

	@Autowired
	public TransactionHistoricalServiceImpl(TransactionViewRepository transactionViewRepository) {
		this.transactionViewRepository = transactionViewRepository;
	}
	

}
