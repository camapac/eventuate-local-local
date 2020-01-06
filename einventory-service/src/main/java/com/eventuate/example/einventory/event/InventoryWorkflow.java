package com.eventuate.example.einventory.event;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventuate.example.einventory.service.IInventoryService;
import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.command.InStockCommand;
import com.eventuate.example.info.command.OutOfStockCommand;
import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventSubscriber(id = "inventoryWorkflow")
public class InventoryWorkflow {
	
	@Autowired
	private IInventoryService inventoryService;

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<Transaction>> confirmTransaction(
			EventHandlerContext<ConfirmTransactionEvent> ctx) {

		ConfirmTransactionEvent event = ctx.getEvent();
		log.info("Check the stock. Received confirm event={}", JsonUtils.objectToString(event));
		boolean result = inventoryService.checkInventory(event);
		if (result) {
			log.info("The order instock!");
			return ctx.update(Transaction.class, event.getId(), new InStockCommand(event.getId()));
		} 
		log.info("The order outofstock!");
		return ctx.update(Transaction.class, event.getId(), new OutOfStockCommand(event.getId()));

	}
}
