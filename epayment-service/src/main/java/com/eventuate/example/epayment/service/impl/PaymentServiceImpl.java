package com.eventuate.example.epayment.service.impl;

import java.util.Random;

import com.eventuate.example.constant.MessageConstant;
import com.eventuate.example.constant.TransactionState;
import com.eventuate.example.entity.Transaction;
import com.eventuate.example.epayment.service.IPaymentService;
import com.eventuate.example.exception.BusinessLogicException;
import com.eventuate.example.info.command.TransactionCommand;
import com.eventuate.example.info.event.TransactionInstockEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.sync.AggregateRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentServiceImpl implements IPaymentService {
	
	private final AggregateRepository<Transaction, TransactionCommand> transactionRepository;

	public PaymentServiceImpl(AggregateRepository<Transaction, TransactionCommand> transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public boolean checkPaymentEligible(TransactionInstockEvent event) {
		log.info("Check inventory ={}",JsonUtils.objectToString(event));
		
		Transaction trans = transactionRepository.find(event.getId()).getEntity();
		if (trans == null) 
			throw new BusinessLogicException(MessageConstant.NOT_FOUND, MessageConstant.NOT_FOUND);
		if (trans.getState().equals(TransactionState.CONFIRMED) || trans.getState().equals(TransactionState.RESERVED)) {
			Random random = new Random();
		    int val = random.nextInt(2);
		    if (val % 2 == 0) return true;
			return true;
		}
		log.info("PaymentService not allow change or update new state!");
		return false;
	}
}
