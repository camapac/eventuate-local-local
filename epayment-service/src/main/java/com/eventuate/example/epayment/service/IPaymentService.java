package com.eventuate.example.epayment.service;

import com.eventuate.example.info.event.TransactionInstockEvent;

public interface IPaymentService {

	public boolean checkPaymentEligible(TransactionInstockEvent event);

}
