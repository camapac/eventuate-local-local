package com.eventuate.example.ehistorical.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.eventuate.example.ehistorical.repository.TransactionViewRepositoryCustom;

public class TransactionViewRepositoryImpl implements TransactionViewRepositoryCustom {

	private MongoTemplate mongoTemplate;

	@Autowired
	public TransactionViewRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
