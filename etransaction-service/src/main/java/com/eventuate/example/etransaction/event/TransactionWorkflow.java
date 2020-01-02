package com.eventuate.example.etransaction.event;

import java.util.concurrent.CompletableFuture;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.command.CreateTransactionCommand;
import com.eventuate.example.info.event.CreateTransactionEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventSubscriber(id = "transacitonWorkflow")
public class TransactionWorkflow {

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<Transaction>> createTransaction(
			EventHandlerContext<CreateTransactionEvent> ctx) {

		CreateTransactionEvent event = ctx.getEvent();
		log.info("Received event={}", JsonUtils.objectToString(event));
		return ctx.update(Transaction.class, event.getOrderCode(), new CreateTransactionCommand());
	}
}
