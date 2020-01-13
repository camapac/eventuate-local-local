package com.eventuate.example.ehistorical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.eventuate.example.ehistorical.repository")
@EnableEventHandlers
@Import({ EventuateDriverConfiguration.class})
public class HistoricalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoricalApplication.class, args);
	}
}
