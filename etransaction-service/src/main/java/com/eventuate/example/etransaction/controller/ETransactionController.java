package com.eventuate.example.etransaction.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventuate.example.constant.MessageConstant;
import com.eventuate.example.entity.Transaction;
import com.eventuate.example.etransaction.service.ITransactionService;
import com.eventuate.example.exception.BusinessLogicException;
import com.eventuate.example.info.CreateTransactionRequest;
import com.eventuate.example.info.TransactionConfirmRequest;
import com.eventuate.example.info.TransactionResponse;
import com.eventuate.example.utils.JsonUtils;
import com.eventuate.example.utils.UniqueRandomIdGenerator;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/transaction")
@RestController
public class ETransactionController {
	
	@Autowired
	private ITransactionService transactionService;
	
	
	@PostMapping
	public ResponseEntity<TransactionResponse> initTransaction(@RequestBody CreateTransactionRequest request) {
		if (request == null) 
			throw new BusinessLogicException(MessageConstant.BAD_REQUEST);
		log.info("Request Create Transaction ={}",JsonUtils.objectToString(request));
		if (StringUtils.isEmpty(request.getOrderCode())) 
			request.setOrderCode(UniqueRandomIdGenerator.getUniqueRandomId(UniqueRandomIdGenerator.getCurrentPrefix(), UniqueRandomIdGenerator.Type.PREFIX));
		EntityWithIdAndVersion<Transaction> entity = transactionService.createInitTransaction(request);
		return ResponseEntity.ok(toTransactionResponse(entity));
	}
	
	@PostMapping(path="/confirm")
	public ResponseEntity<TransactionResponse> confirmTransactionCommand(@RequestBody TransactionConfirmRequest request) {
		if (request == null) 
			throw new BusinessLogicException(MessageConstant.BAD_REQUEST);
		log.info("Request Confirm Transaction ={}",JsonUtils.objectToString(request));
		EntityWithIdAndVersion<Transaction>  entityMeta = transactionService.confirmTransaction(request);
		return ResponseEntity.ok(toTransactionResponse(entityMeta));
	}
	
	@GetMapping(path="/{transaction-id}")
	public ResponseEntity<TransactionResponse> findTransaction(@PathVariable("transaction-id") String transactionId) {
		if (StringUtils.isAllBlank(transactionId)) 
			throw new BusinessLogicException(MessageConstant.BAD_REQUEST);
		EntityWithMetadata<Transaction> entityMeta = transactionService.findById(transactionId);
		return ResponseEntity.ok(toTransactionResponse(entityMeta.toEntityWithIdAndVersion()));

	} 

	@GetMapping
	public ResponseEntity<String> testController(){
		return ResponseEntity.ok("Hello");
	}
	
	
	private TransactionResponse toTransactionResponse(EntityWithIdAndVersion<Transaction> entityMeta) {
		TransactionResponse response = new TransactionResponse();
		BeanUtils.copyProperties(entityMeta.getAggregate(), response);
		response.setId(entityMeta.getEntityId());
		log.info("Response={}",JsonUtils.objectToString(response));
		return response;
	}
}
