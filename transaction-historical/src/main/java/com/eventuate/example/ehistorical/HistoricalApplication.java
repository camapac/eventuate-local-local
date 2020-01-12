package com.eventuate.example.ehistorical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.eventuate.example.ehistorical.configuration.HistoricalTransactionConfiguration;

@Configuration
@Import({HistoricalTransactionConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class HistoricalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoricalApplication.class, args);
	}
}
