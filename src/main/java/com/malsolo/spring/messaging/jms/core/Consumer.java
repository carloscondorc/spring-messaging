package com.malsolo.spring.messaging.jms.core;

import com.malsolo.spring.messaging.model.Information;


public interface Consumer {
	
	public Information receive() throws Exception;
	
	public void doReceive() throws Exception;

}
