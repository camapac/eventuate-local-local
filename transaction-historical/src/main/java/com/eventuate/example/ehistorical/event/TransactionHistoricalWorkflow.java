package com.eventuate.example.ehistorical.event;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventuate.example.ehistorical.service.ITransactionHistoricalService;
import com.eventuate.example.info.command.CreateTransactionCommand;
import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.info.event.CreateTransactionEvent;
import com.eventuate.example.info.event.TransactionInstockEvent;
import com.eventuate.example.info.event.TransactionOutstockEvent;
import com.eventuate.example.info.event.TransactionPaidEvent;
import com.eventuate.example.info.event.TransactionRollbackStockEvent;
import com.eventuate.example.info.event.TransactionUnPaidEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@EventSubscriber(id = "transactionViewWorkflow")
public class TransactionHistoricalWorkflow {

	private ITransactionHistoricalService transactionService;

	@Autowired
	public TransactionHistoricalWorkflow(ITransactionHistoricalService transactionService) {
		this.transactionService = transactionService;
	}

	@EventHandlerMethod
	public void createTransaction(DispatchedEvent<CreateTransactionEvent> ctx) {
		CreateTransactionEvent event = ctx.getEvent();
		log.info("Received event={}", JsonUtils.objectToString(event));
		CreateTransactionCommand cmd = new CreateTransactionCommand();
		BeanUtils.copyProperties(event, cmd);
	}

	@EventHandlerMethod
	public void confirmTransaction(DispatchedEvent<ConfirmTransactionEvent> ctx) {

		ConfirmTransactionEvent event = ctx.getEvent();
		log.info("Received confirm event={}", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void transactionInStock(DispatchedEvent<TransactionInstockEvent> ctx) {

		TransactionInstockEvent event = ctx.getEvent();
		log.info("The transaction instock. Received confirm event={}", JsonUtils.objectToString(event));

	}
	
	public void transactionOutstock(DispatchedEvent<TransactionOutstockEvent> ctx) {
		TransactionOutstockEvent event = ctx.getEvent();
		log.info("The transaction out of stock. Received confirm event={}", JsonUtils.objectToString(event));

	}

	@EventHandlerMethod
	public void onPaymentSuccess(DispatchedEvent<TransactionPaidEvent> ctx) {

		TransactionPaidEvent event = ctx.getEvent();
		log.info("Received success payment transaciton event", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void onPaymentFailure(DispatchedEvent<TransactionUnPaidEvent> ctx) {

		TransactionUnPaidEvent event = ctx.getEvent();
		log.info("Received failure payment transaciton event", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void rollbackTheStock(DispatchedEvent<TransactionRollbackStockEvent> ctx) {
		TransactionRollbackStockEvent event = ctx.getEvent();
		log.info("Check the stock. rollback the event={}", JsonUtils.objectToString(event));

	}

}
