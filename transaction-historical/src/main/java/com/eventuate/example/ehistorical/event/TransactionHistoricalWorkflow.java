package com.eventuate.example.ehistorical.event;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import io.eventuate.sync.EventHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventSubscriber(id = "transactionViewWorkflow")
public class TransactionHistoricalWorkflow {

	private ITransactionHistoricalService transactionService;

	@Autowired
	public TransactionHistoricalWorkflow(ITransactionHistoricalService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@EventHandlerMethod
	public void createTransaction(EventHandlerContext<CreateTransactionEvent> ctx) {
		CreateTransactionEvent event = ctx.getEvent();
		log.info("Received event={}", JsonUtils.objectToString(event));
		CreateTransactionCommand cmd = new CreateTransactionCommand();
		BeanUtils.copyProperties(event, cmd);
	}

	@EventHandlerMethod
	public void confirmTransaction(EventHandlerContext<ConfirmTransactionEvent> ctx) {

		ConfirmTransactionEvent event = ctx.getEvent();
		log.info("Received confirm event={}", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void transactionInStock(EventHandlerContext<TransactionInstockEvent> ctx) {

		TransactionInstockEvent event = ctx.getEvent();
		log.info("The transaction instock. Received confirm event={}", JsonUtils.objectToString(event));

	}
	
	public void transactionOutstock(EventHandlerContext<TransactionOutstockEvent> ctx) {
		TransactionOutstockEvent event = ctx.getEvent();
		log.info("The transaction out of stock. Received confirm event={}", JsonUtils.objectToString(event));

	}

	@EventHandlerMethod
	public void onPaymentSuccess(EventHandlerContext<TransactionPaidEvent> ctx) {

		TransactionPaidEvent event = ctx.getEvent();
		log.info("Received success payment transaciton event", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void onPaymentFailure(EventHandlerContext<TransactionUnPaidEvent> ctx) {

		TransactionUnPaidEvent event = ctx.getEvent();
		log.info("Received failure payment transaciton event", JsonUtils.objectToString(event));
	}

	@EventHandlerMethod
	public void rollbackTheStock(EventHandlerContext<TransactionRollbackStockEvent> ctx) {
		TransactionRollbackStockEvent event = ctx.getEvent();
		log.info("Check the stock. rollback the event={}", JsonUtils.objectToString(event));

	}

}
