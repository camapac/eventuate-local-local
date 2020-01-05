package com.eventuate.example.info.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionPaidEvent implements TransactionEvent {

	private String id;
}
