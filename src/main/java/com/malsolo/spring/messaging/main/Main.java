package com.malsolo.spring.messaging.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.malsolo.spring.messaging.jms.core.Consumer;
import com.malsolo.spring.messaging.jms.core.Producer;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String... args) throws Exception {
		
		logger.info("***** MAIN JMS *****");
		
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
//		ctx.start();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(); 
		ctx.load("classpath:/META-INF/spring/applicationContext.xml");
		ctx.refresh();
		
		short s = 3;
		
		Producer producer = ctx.getBean(Producer.class);
		Consumer consumer = ctx.getBean(Consumer.class);

		for (int i = 0; i < s; i++) {
			producer.doSend();
		}
		for (int i = 0; i < s; i++) {
			consumer.doReceive();
		}

		logger.info("***** WAITING MAIN JMS *****");
		
		Thread.sleep(30000);
		
		logger.info("***** END MAIN JMS *****");

	}

}
