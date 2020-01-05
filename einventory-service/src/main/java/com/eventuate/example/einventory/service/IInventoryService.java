package com.eventuate.example.einventory.service;

import com.eventuate.example.info.event.ConfirmTransactionEvent;

public interface IInventoryService {
	
	public boolean checkInventory(ConfirmTransactionEvent event);
}
