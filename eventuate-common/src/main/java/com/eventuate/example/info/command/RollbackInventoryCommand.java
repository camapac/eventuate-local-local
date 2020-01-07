package com.eventuate.example.info.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RollbackInventoryCommand implements TransactionCommand {

	private String id;
}
