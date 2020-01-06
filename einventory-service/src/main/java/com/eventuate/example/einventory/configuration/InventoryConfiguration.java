package com.eventuate.example.einventory.configuration;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.eventuate.example.einventory.event.InventoryWorkflow;
import com.eventuate.example.einventory.service.IInventoryService;
import com.eventuate.example.einventory.service.impl.IInventoryServiceImpl;
import com.eventuate.example.entity.Transaction;
import com.eventuate.example.info.command.TransactionCommand;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;

@Configuration
@EnableEventHandlers
@ComponentScan
public class InventoryConfiguration {

	@Bean
	public HttpMessageConverters customConverters() {
		HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
		return new HttpMessageConverters(additional);
	}

	@Bean(name = "inventoryWorkflow")
	public InventoryWorkflow inventoryWorkflow() {
		return new InventoryWorkflow();
	}

	@Bean
	public IInventoryService orderService(AggregateRepository<Transaction, TransactionCommand> transactionRepository) {
		return new IInventoryServiceImpl(transactionRepository);
	}

	@Bean(name = "transactionRepository")
	public AggregateRepository<Transaction, TransactionCommand> transactionRepository(
			EventuateAggregateStore eventStore) {
		return new AggregateRepository<>(Transaction.class, eventStore);
	}
}
