package com.priest.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelProducer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
}
