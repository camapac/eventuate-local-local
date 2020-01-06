package com.eventuate.example.info.event;

import com.eventuate.example.constant.ReverseType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionReverseEvent implements TransactionEvent {

	private String id;
	private ReverseType reverseType;

}
