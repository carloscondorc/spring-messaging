package com.malsolo.spring.messaging.jms.core;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.stereotype.Component;

import com.malsolo.spring.messaging.model.Information;

@Component
public class InformationMessageConverter {
	
	public static String MAP_MESSAGE_NAME_ID = "id";
	public static String MAP_MESSAGE_NAME_DATA = "data";

	public Message fromInformation(Session session, Information information) throws JMSException {
		MapMessage mapMessage = session.createMapMessage();
		mapMessage.setLong(MAP_MESSAGE_NAME_ID, information.getId());
		mapMessage.setString(MAP_MESSAGE_NAME_DATA, information.getData());
		return mapMessage;
	}
	  
	  public Information fromMessage(MapMessage message) throws JMSException {
		  return new Information(message.getLong(MAP_MESSAGE_NAME_ID), message.getString(MAP_MESSAGE_NAME_DATA));
	  } 

}
