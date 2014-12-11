package constellation.virgo.spring.template.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		System.out.println("--开始监听消息队列--");
		try {
			System.out.println("接收到的消息" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
