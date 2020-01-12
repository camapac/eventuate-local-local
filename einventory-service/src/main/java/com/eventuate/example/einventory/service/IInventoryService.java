package com.eventuate.example.einventory.service;

import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.info.event.TransactionRollbackStockEvent;

public interface IInventoryService {
	
	public boolean checkInventory(ConfirmTransactionEvent event);

	public void rollbackTheStock(TransactionRollbackStockEvent event);
}
