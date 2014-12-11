package org.season.autumn.jms.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerMessageListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapmessage = (MapMessage) message;
			try {
				String fileid = mapmessage.getString("fileid");
				System.out.println("File ID为:" + fileid);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
