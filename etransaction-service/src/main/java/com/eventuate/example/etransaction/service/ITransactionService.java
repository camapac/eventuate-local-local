package com.eventuate.example.etransaction.service;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.CreateTransactionRequest;
import com.eventuate.example.info.TransactionConfirmRequest;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;

public interface ITransactionService {

	EntityWithIdAndVersion<Transaction> createInitTransaction(CreateTransactionRequest request);
	
	EntityWithIdAndVersion<Transaction> confirmTransaction(TransactionConfirmRequest request);
	
	EntityWithMetadata<Transaction> findById(String transactionId);
}
