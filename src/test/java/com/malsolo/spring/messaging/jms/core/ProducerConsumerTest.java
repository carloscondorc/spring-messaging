package com.malsolo.spring.messaging.jms.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.malsolo.spring.messaging.model.Information;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring/applicationContext.xml"})
public class ProducerConsumerTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private Consumer consumer;
	
	@Test
	public void testMarkerMethods() {
	}
	
	@Test
	public void testProducer() {
		Information information = new Information(1, "UNO");
		boolean debugEnabled = logger.isDebugEnabled();
		String info = information.toString();
		if (debugEnabled) logger.debug("TEST producer. Sending "+info);
		this.producer.send(information);
		if (debugEnabled) logger.debug("TEST producer. Done, a information has been sent: "+info);
	}

	@Test
	public void testConsumer() throws Exception {
		boolean debugEnabled = logger.isDebugEnabled();
		if (debugEnabled) logger.debug("TEST consumer. Receiving Information... ");
		Information information = this.consumer.receive();
		String info = information.toString();
		if (debugEnabled) logger.debug("TEST consumer. Done, a information has been received: "+info);
	}

	@Test
	public void testProducerConsumer() throws Exception {
		
		boolean debugEnabled = logger.isDebugEnabled();

		Information information = new Information(1, "UNO");
		this.producer.send(information);
		
		information = this.consumer.receive();
		
		if (debugEnabled) logger.debug("TEST producer/consumer: exchanged "+information);
	}
}
