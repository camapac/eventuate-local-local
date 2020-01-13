package com.eventuate.example.ehistorical.repository;

import com.eventuate.example.constant.TransactionState;

public interface TransactionViewRepositoryCustom {

	public void updateTheState(String id, TransactionState state);
}
