package com.eventuate.example.ehistorical.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eventuate.example.ehistorical.document.TransactionView;

@Repository
public interface TransactionViewRepository extends MongoRepository<TransactionView, String>, TransactionViewRepositoryCustom {

}
