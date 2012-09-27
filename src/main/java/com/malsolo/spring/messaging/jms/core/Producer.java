package com.malsolo.spring.messaging.jms.core;

import com.malsolo.spring.messaging.model.Information;

public interface Producer {

	public abstract void send(Information information);

}