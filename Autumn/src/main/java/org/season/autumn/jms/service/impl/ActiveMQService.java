package org.season.autumn.jms.service.impl;

import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.season.autumn.jms.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("activeMQService")
public class ActiveMQService implements MessageQueueService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void sendMessage(Destination destination, final String message) {

		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});

	}

	@Override
	public void sendMessage(Destination destination,
			final Map<String, String> mapmessage) {

		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				for (String key : mapmessage.keySet()) {
					message.setString(key, mapmessage.get(key));
				}
				return message;
			}
		});

	}

}
