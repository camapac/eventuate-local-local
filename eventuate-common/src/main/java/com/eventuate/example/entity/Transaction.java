package com.eventuate.example.entity;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.eventuate.example.constant.TransactionState;
import com.eventuate.example.info.command.ConfirmTransactionCommand;
import com.eventuate.example.info.command.CreateTransactionCommand;
import com.eventuate.example.info.command.TransactionCommand;
import com.eventuate.example.info.event.ConfirmTransactionEvent;
import com.eventuate.example.info.event.CreateTransactionEvent;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction extends ReflectiveMutableCommandProcessingAggregate<Transaction, TransactionCommand> {

	private String orderCode;
	private String customerId;
	private Double totalPrice;
	private String noted;
	private TransactionState state;

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
		this.state = TransactionState.INT;
	}
	
	
	public List<Event> process(ConfirmTransactionCommand cmd) {
		ConfirmTransactionEvent event = new ConfirmTransactionEvent();
		BeanUtils.copyProperties(cmd, event);
		return EventUtil.events(event);
	}

	public void apply(ConfirmTransactionEvent event) {
		this.state = TransactionState.CONFIRMED;
	}
}
