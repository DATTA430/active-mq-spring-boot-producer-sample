package com.naive.programmer.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActiveMqSpringBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqSpringBootSampleApplication.class, args);
	}

}
