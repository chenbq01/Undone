package org.season.autumn.jms.service;

import java.util.Map;

import javax.jms.Destination;

public interface MessageQueueService {

	public void sendMessage(Destination destination, final String textmessage);
	
	public void sendMessage(Destination destination, final Map<String,String> mapmessage);

}
