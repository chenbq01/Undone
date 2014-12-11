package com.monster.framework.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// ��������֪�������߷��͵ľ���һ�����ı���Ϣ�������������ֱ�ӽ���ǿ��ת��������ֱ�Ӱ�onMessage�����Ĳ����ĳ�Message������TextMessage
		TextMessage textMsg = (TextMessage) message;
		System.out.println("���յ�һ�����ı���Ϣ��");
		try {
			System.out.println("��Ϣ�����ǣ�" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
