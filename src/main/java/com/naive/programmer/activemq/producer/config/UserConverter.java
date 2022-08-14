package com.naive.programmer.activemq.producer.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naive.programmer.activemq.model.User;

public class UserConverter implements MessageConverter {
	
	private static final Logger logger =
		      LoggerFactory.getLogger(UserConverter.class);
	
	  @Autowired
	  private  ObjectMapper mapper;

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		User user = (User) object;
		String payload = null;
		try {
			payload = mapper.writeValueAsString(user);
			logger.info("outbound msg : {}",payload);
		}catch (Exception e) {
			logger.error("Error while converting from User ",e);
		}
		TextMessage message = session.createTextMessage();
		message.setText(payload);
		
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		TextMessage textMessage = (TextMessage) message;
		String payload = textMessage.getText();
		logger.info("inbound message: {}",payload);
		
		User user = null;
		try {
			user = mapper.readValue(payload, User.class);
		}catch (Exception e) {
			logger.error("Error while converting to User ",e);
		}
		
		return user;
	}

}
