package com.eventuate.example.info.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.eventuate.example.entity.Transaction")
public interface TransactionEvent extends Event {

}
