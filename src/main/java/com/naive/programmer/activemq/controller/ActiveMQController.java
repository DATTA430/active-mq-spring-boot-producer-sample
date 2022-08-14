package com.naive.programmer.activemq.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naive.programmer.activemq.model.User;



@RestController
public class ActiveMQController {
	private static final  Logger logger = Logger.getLogger(ActiveMQController.class.getName());
	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping("/sendUser")
	public User sendUser(@RequestBody User user) {
		logger.info("Inside sendUser Method");
		jmsTemplate.convertAndSend("outbound.queue",user);
		return user;
	}
}
