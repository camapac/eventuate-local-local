package com.eventuate.example.etransaction.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventuate.example.constant.MessageConstant;
import com.eventuate.example.entity.Transaction;
import com.eventuate.example.etransaction.service.ITransactionService;
import com.eventuate.example.exception.ApplicationException;
import com.eventuate.example.info.CreateTransactionRequest;
import com.eventuate.example.info.TransactionConfirmRequest;
import com.eventuate.example.info.command.ConfirmTransactionCommand;
import com.eventuate.example.info.command.CreateTransactionCommand;
import com.eventuate.example.info.command.TransactionCommand;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.eventuate.sync.AggregateRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements ITransactionService {

	private final AggregateRepository<Transaction, TransactionCommand> transactionRepository;

	@Autowired
	public TransactionServiceImpl(AggregateRepository<Transaction, TransactionCommand> transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public EntityWithIdAndVersion<Transaction> createInitTransaction(CreateTransactionRequest request) {
		CreateTransactionCommand cmd = new CreateTransactionCommand();
		BeanUtils.copyProperties(request, cmd);
		try {
			log.info("SaveTransactionCMD ={}",JsonUtils.objectToString(request));
			EntityWithIdAndVersion<Transaction> future =   transactionRepository.save(cmd);
			log.info("Save transaction !!!!");
			return future;
		} catch (Exception e) {
			log.error(e.getMessage()+"={}",e.getCause());
			throw new ApplicationException(MessageConstant.GENERAL_ERROR, MessageConstant.GENERAL_ERROR);
		}
	}

	@Override
	public EntityWithIdAndVersion<Transaction> confirmTransaction(TransactionConfirmRequest request) {
		ConfirmTransactionCommand cmd = new ConfirmTransactionCommand();
		BeanUtils.copyProperties(request, cmd);
		EntityWithIdAndVersion<Transaction> future =   transactionRepository.update(request.getId(), cmd);
		log.info("Confrim transaction !!!!");
		return future;
	}

	@Override
	public EntityWithMetadata<Transaction> findById(String transactionId) {
		return transactionRepository.find(transactionId);
	}
	
	
	
	

}
