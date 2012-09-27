package com.malsolo.spring.messaging.jms.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.malsolo.spring.messaging.model.Information;

@Component
public class ProducerImpl implements Producer {
	
	@Value("${jms.destination}")
	private String destination;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private InformationMessageConverter informationMessageConverter;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Transactional("jmsTransactionManager")
	@Override
	public void send(final Information information) {
		
		this.jmsTemplate.send(this.destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				logger.info("Sending customer data " + ToStringBuilder.reflectionToString(information));
				return informationMessageConverter.fromInformation(session, information);
			}
		});
		
	} 

}
