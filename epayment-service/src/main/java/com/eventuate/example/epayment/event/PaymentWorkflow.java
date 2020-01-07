package com.eventuate.example.epayment.event;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.epayment.service.IPaymentService;
import com.eventuate.example.info.command.PaymentFailureCommand;
import com.eventuate.example.info.command.PaymentSuccessCommand;
import com.eventuate.example.info.event.TransactionInstockEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventSubscriber(id = "paymentWorkflow")
public class PaymentWorkflow {

	@Autowired
	private IPaymentService paymentService;

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<Transaction>> confirmTransaction(
			EventHandlerContext<TransactionInstockEvent> ctx) {

		TransactionInstockEvent event = ctx.getEvent();
		log.info("Check the stock. Received confirm event={}", JsonUtils.objectToString(event));
		boolean result = paymentService.checkPaymentEligible(event);
		if (result) {
			log.info("The transaction eligible for payment!");
			return ctx.update(Transaction.class, event.getId(), new PaymentSuccessCommand(event.getId()));
		} 
		log.info("The transaction not eligible for payment!");
		return ctx.update(Transaction.class, event.getId(), new PaymentFailureCommand(event.getId()));

	}
}
