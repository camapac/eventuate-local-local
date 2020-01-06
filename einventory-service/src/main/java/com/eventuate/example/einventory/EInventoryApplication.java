package com.eventuate.example.einventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.eventuate.example.einventory.configuration.InventoryConfiguration;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
@Configuration
@Import({InventoryConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class EInventoryApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EInventoryApplication.class, args);
	}
}
