package org.season.autumn.jms.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerMessageListener implements MessageListener {

	private static final Logger logger = LoggerFactory
			.getLogger(ConsumerMessageListener.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapmessage = (MapMessage) message;
			try {
				String fileid = mapmessage.getString("fileid");
				if (logger.isDebugEnabled()) {
					logger.debug("File ID为:" + fileid);
				}
			} catch (JMSException e) {
				logger.error(e.getMessage());
			}
		}
	}

}
