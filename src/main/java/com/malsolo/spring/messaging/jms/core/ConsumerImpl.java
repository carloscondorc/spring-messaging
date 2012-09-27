package com.malsolo.spring.messaging.jms.core;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.malsolo.spring.messaging.model.Information;

@Component
public class ConsumerImpl implements Consumer {

	@Value("${jms.destination}")
	private String destination;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private InformationMessageConverter informationMessageConverter;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Transactional("jmsTransactionManager")
	@Override
	public Information receive() throws Exception {
		
		Information information = null;
		
		Message message = this.jmsTemplate.receive(this.destination);
		if (message instanceof MapMessage) {
			information = this.informationMessageConverter.fromMessage( (MapMessage) message);
			logger.info("receiving customer message: " + information);
		}
		
		if (information != null) {
			return information;
		}
		else {
			throw new Exception("Can't receive information " + (message!=null?message.toString():"message null"));
		}
		
	}

}
