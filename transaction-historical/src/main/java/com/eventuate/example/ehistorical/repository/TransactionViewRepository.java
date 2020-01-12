package com.eventuate.example.ehistorical.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eventuate.example.ehistorical.document.TransactionView;

public interface TransactionViewRepository extends MongoRepository<TransactionView, String>, TransactionViewRepositoryCustom {

}
