package com.eventuate.example.epayment.event;

import org.springframework.stereotype.Component;

import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EventSubscriber(id = "paymentWorkflow")
public class PaymentWorkflow {

}
