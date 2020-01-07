package com.eventuate.example.entity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.eventuate.example.constant.TransactionState;
import com.eventuate.example.info.command.ConfirmTransactionCommand;
import com.eventuate.example.info.command.CreateTransactionCommand;
import com.eventuate.example.info.command.FailureTransactionCommand;
import com.eventuate.example.info.command.InStockCommand;
import com.eventuate.example.info.command.OutOfStockCommand;
import com.eventuate.example.info.command.PaymentFailureCommand;
import com.eventuate.example.info.command.PaymentSuccessCommand;
import com.eventuate.example.info.command.RollbackInventoryCommand;
import com.eventuate.example.info.command.SuccessTransactionCommand;
import com.eventuate.example.info.command.TransactionCommand;
import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.info.event.CreateTransactionEvent;
import com.eventuate.example.info.event.TransactionFailureEvent;
import com.eventuate.example.info.event.TransactionInstockEvent;
import com.eventuate.example.info.event.TransactionOutstockEvent;
import com.eventuate.example.info.event.TransactionPaidEvent;
import com.eventuate.example.info.event.TransactionRollbackPaymentEvent;
import com.eventuate.example.info.event.TransactionRollbackStockEvent;
import com.eventuate.example.info.event.TransactionSuccessEvent;
import com.eventuate.example.info.event.TransactionUnPaidEvent;
import com.eventuate.example.utils.JsonUtils;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction extends ReflectiveMutableCommandProcessingAggregate<Transaction, TransactionCommand> {

	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	private String reason;
	private Date inventoryReservedDate;
	private Date paymentResultDate;
	private TransactionState preState;
	private Date createdDate = new Date(System.currentTimeMillis());
	private TransactionState state = TransactionState.INIT; // init order and default

	public List<Event> process(CreateTransactionCommand cmd) {
		CreateTransactionEvent event = new CreateTransactionEvent();
		BeanUtils.copyProperties(cmd, event);
		return EventUtil.events(event);
	}

	public void apply(CreateTransactionEvent event) {
		this.orderCode = event.getOrderCode();
		this.customerId = event.getCustomerId();
		this.totalPrice = event.getTotalPrice();
		this.noted = event.getNoted();
		this.state = TransactionState.INIT;
	}

	// Confirm the order
	public List<Event> process(ConfirmTransactionCommand cmd) {
		log.info("Emit new confirm transaction cmd={}", JsonUtils.objectToString(cmd));
		return EventUtil.events(new ConfirmTransactionEvent(cmd.getId()));
	}

	public void apply(ConfirmTransactionEvent event) {
		this.preState = this.state;
		this.state = TransactionState.CONFIRMED;
	}

	// The order instock
	public List<Event> process(InStockCommand cmd) {
		return EventUtil.events(new TransactionInstockEvent(cmd.getId()));
	}

	public void apply(TransactionInstockEvent event) {
		this.preState = this.state;
		this.state = TransactionState.RESERVED;
		this.inventoryReservedDate = new Date(System.currentTimeMillis());
	}

	// The order out ofstock
	public List<Event> process(OutOfStockCommand cmd) {
		return EventUtil.events(new TransactionOutstockEvent(cmd.getId()));

	}

	public void apply(TransactionOutstockEvent event) {
		this.preState = this.state;
		this.state = TransactionState.UNRESERVED;
		this.inventoryReservedDate = new Date(System.currentTimeMillis());

	}

	// The payment failure
	public List<Event> process(PaymentFailureCommand cmd) {
		return EventUtil.events(new TransactionUnPaidEvent(cmd.getId()));
	}

	public void apply(TransactionUnPaidEvent event) {
		this.preState = this.state;
		this.state = TransactionState.UNPAID;
		this.paymentResultDate = new Date(System.currentTimeMillis());
		// checkAndUpdateFailure(this, event.getId());
	}

	// The payment success
	public List<Event> process(PaymentSuccessCommand cmd) {
		return EventUtil.events(new TransactionPaidEvent(cmd.getId()));
	}

	public void apply(TransactionPaidEvent event) {
		this.preState = this.state;
		this.state = TransactionState.PAID;
		this.paymentResultDate = new Date(System.currentTimeMillis());
		// checkAndUpdateSuccess(this, event.getId());
	}

	private void checkAndUpdateSuccess(Transaction transaction, String id) {
		log.info("Check success transaction ={}", JsonUtils.objectToString(transaction));
		if (transaction.getState().equals(TransactionState.PAID)
				&& transaction.getPreState().equals(TransactionState.RESERVED)
				|| (transaction.getState().equals(TransactionState.RESERVED)
						&& transaction.getPreState().equals(TransactionState.PAID))) {
			this.preState = transaction.getState();
			this.state = TransactionState.SUCCESS;
			EventUtil.events(new TransactionSuccessEvent(id));
		}
	}

	private void checkAndUpdateFailure(Transaction transaction, String id) {
		log.info("Check failure transaction ={}", JsonUtils.objectToString(transaction));
		switch (transaction.getState()) {
		case UNRESERVED:
			if (preState.equals(TransactionState.PAID)) {
				EventUtil.events(new TransactionRollbackPaymentEvent(id));
			}
			failureTransaction(transaction, id);
			break;
		case UNPAID:
			if (preState.equals(TransactionState.RESERVED)) {
				EventUtil.events(new TransactionRollbackStockEvent(id));
			}
			failureTransaction(transaction, id);
			break;
		default:
			break;
		}
	}

	private void failureTransaction(Transaction transaction, String id) {
		log.info("Update the transaction failure");
		this.preState = transaction.getState();
		this.state = TransactionState.FAILURE;
		EventUtil.events(new TransactionFailureEvent(id));
	}

	// The payment transaction event
	public List<Event> process(SuccessTransactionCommand cmd) {
		return EventUtil.events(new TransactionSuccessEvent(cmd.getId()));

	}

	public void apply(TransactionSuccessEvent event) {
		log.info("Apply transaction success");
		this.preState = this.state;
		this.state = TransactionState.SUCCESS;

	}

	// The failure transaction event
	public List<Event> process(FailureTransactionCommand cmd) {
		return EventUtil.events(new TransactionFailureEvent(cmd.getId()));

	}

	public void apply(TransactionFailureEvent event) {
		log.info("Apply transaction failure");
		this.preState = this.state;
		this.state = TransactionState.FAILURE;
	}

	// The rollback event
	public List<Event> process(RollbackInventoryCommand cmd) {
		return EventUtil.events(new TransactionRollbackStockEvent(cmd.getId()));

	}

	public void apply(TransactionRollbackStockEvent event) {
		
	}
}
