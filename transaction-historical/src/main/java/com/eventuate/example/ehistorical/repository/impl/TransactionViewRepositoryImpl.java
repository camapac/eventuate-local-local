package com.eventuate.example.ehistorical.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.eventuate.example.constant.TransactionState;
import com.eventuate.example.ehistorical.document.TransactionView;
import com.eventuate.example.ehistorical.repository.TransactionViewRepositoryCustom;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class TransactionViewRepositoryImpl implements TransactionViewRepositoryCustom {

	private MongoTemplate mongoTemplate;

	@Autowired
	public TransactionViewRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void updateTheState(String id, TransactionState state) {
		 mongoTemplate.upsert(new Query(where("id").is(id)),
		            new Update().set("state", state)
		            , TransactionView.class);

	}
	
	
}
