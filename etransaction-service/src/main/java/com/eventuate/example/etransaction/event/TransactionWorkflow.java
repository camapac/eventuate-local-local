package com.eventuate.example.etransaction.event;

import java.util.concurrent.CompletableFuture;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.command.RollbackInventoryCommand;
import com.eventuate.example.info.command.SuccessTransactionCommand;
import com.eventuate.example.info.event.TransactionPaidEvent;
import com.eventuate.example.info.event.TransactionUnPaidEvent;
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
			EventHandlerContext<TransactionPaidEvent> ctx) {

		TransactionPaidEvent event = ctx.getEvent();
		log.info("Received success payment transaciton event", JsonUtils.objectToString(event));
		return ctx.update(Transaction.class, event.getId(), new SuccessTransactionCommand(event.getId()));
	}
	
	
	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<Transaction>> onPaymentFailure(
			EventHandlerContext<TransactionUnPaidEvent> ctx) {

		TransactionUnPaidEvent event = ctx.getEvent();
		log.info("Received failure payment transaciton event", JsonUtils.objectToString(event));
		return ctx.update(Transaction.class, event.getId(), new RollbackInventoryCommand(event.getId()));
	}
	
	
	
	
	
//	@EventHandlerMethod
//	public CompletableFuture<EntityWithIdAndVersion<Transaction>> inventoryInStock(
//			EventHandlerContext<TransactionInstockEvent> ctx) {
//
//		TransactionInstockEvent event = ctx.getEvent();
//		log.info("Received inventory in stock transaciton event", JsonUtils.objectToString(event));
//		return ctx.update(Transaction.class, event.getId(), new InStockCommand(event.getId()));
//	}
//	
//	
//	@EventHandlerMethod
//	public CompletableFuture<EntityWithIdAndVersion<Transaction>> inventoryOutOfStock(
//			EventHandlerContext<TransactionOutstockEvent> ctx) {
//
//		TransactionOutstockEvent event = ctx.getEvent();
//		log.info("Received inventory out of stock transaciton event", JsonUtils.objectToString(event));
//		return ctx.update(Transaction.class, event.getId(), new OutOfStockCommand(event.getId()));
//	}
//	
}
