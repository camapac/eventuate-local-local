package com.eventuate.example.etransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.eventuate.example.etransaction.configuration.ETransactionConfiguration;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

@Configuration
@Import({ETransactionConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class ETransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETransactionApplication.class, args);
	}

}
