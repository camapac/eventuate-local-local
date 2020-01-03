package com.eventuate.example.etransaction.configuration;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.eventuate.example.entity.Transaction;
import com.eventuate.example.etransaction.event.TransactionWorkflow;
import com.eventuate.example.info.command.TransactionCommand;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@EnableEventHandlers
@ComponentScan
public class ETransactionConfiguration {

	@Bean
	public HttpMessageConverters customConverters() {
		HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
		return new HttpMessageConverters(additional);
	}

	@Bean(name="transactionWorkflow")
	public TransactionWorkflow transactionWorkflow() {
		return new TransactionWorkflow();
	}

	@Bean(name="transactionRepository")
	public AggregateRepository<Transaction, TransactionCommand> transactionRepository(EventuateAggregateStore eventStore) {
		return new AggregateRepository<>(Transaction.class, eventStore);
	}

}
