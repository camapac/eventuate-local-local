package com.eventuate.example.epayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.eventuate.example.epayment.configuration.PaymentConfiguration;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;


@Configuration
@Import({PaymentConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class EPaymentApplication {


	public static void main(String[] args) {
		SpringApplication.run(EPaymentApplication.class, args);
	}

}
