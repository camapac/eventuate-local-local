package com.eventuate.example.etransaction.event;

import java.util.concurrent.CompletableFuture;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.command.ConfirmTransactionCommand;
import com.eventuate.example.info.command.SuccessTransactionCommand;
import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.info.event.TransactionSuccessEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventSubscriber(id = "transactionWorkflow")
public class TransactionWorkflow {

//	@EventHandlerMethod
//	public CompletableFuture<EntityWithIdAndVersion<Transaction>> createTransaction(
//			EventHandlerContext<CreateTransactionEvent> ctx) {
//		CreateTransactionEvent event = ctx.getEvent();
//		log.info("Received event={}", JsonUtils.objectToString(event));
//		CreateTransactionCommand  cmd = new CreateTransactionCommand();
//		BeanUtils.copyProperties(event, cmd);
//		return ctx.update(Transaction.class, event.getOrderCode(), cmd);
//	}
	
	
//	@EventHandlerMethod
//	public CompletableFuture<EntityWithIdAndVersion<Transaction>> confirmTransaction(
//			EventHandlerContext<ConfirmTransactionEvent> ctx) {
//
//		ConfirmTransactionEvent event = ctx.getEvent();
//		log.info("Received confirm event={}", JsonUtils.objectToString(event));
//		return ctx.update(Transaction.class, event.getId(), new ConfirmTransactionCommand(event.getId()));
//	}
	
	
	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<Transaction>> onPaymentSuccess(
			EventHandlerContext<TransactionSuccessEvent> ctx) {

		TransactionSuccessEvent event = ctx.getEvent();
		log.info("Received success transaciton event", JsonUtils.objectToString(event));
		return ctx.update(Transaction.class, event.getId(), new SuccessTransactionCommand(event.getId()));
	}
}
