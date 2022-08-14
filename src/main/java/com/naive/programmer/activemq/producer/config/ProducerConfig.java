package com.naive.programmer.activemq.producer.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ProducerConfig {

	//@Value("${spring.activemq.broker-url}")
	private String brokerUrl = "tcp://localhost:61616";

	
	public ActiveMQConnectionFactory producerConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory producerCachingConnectionFactory() {
		return new CachingConnectionFactory(producerConnectionFactory());
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}

}
