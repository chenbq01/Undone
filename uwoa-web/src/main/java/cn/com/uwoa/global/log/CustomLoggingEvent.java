package cn.com.uwoa.global.log;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLoggingEvent extends LoggingEvent {

	private static final long serialVersionUID = 5671921623722129829L;

	public CustomLoggingEvent(String fqnOfCategoryClass, Category logger,
			Priority level, Object message, Throwable throwable) {
		super(fqnOfCategoryClass, logger, level, message, throwable);
	}

	/**
	 * �߳���������������
	 */
	public String getThreadName() {
		String thrdName = super.getThreadName();
		if (thrdName.indexOf("'") != -1) {
			thrdName = thrdName.replaceAll("'", "''");
		}
		return thrdName;
	}

	/**
	 * �Բ����message�а����ĵ�����(')������
	 * 
	 * @see org.apache.log4j.spi.LoggingEventgetRenderedMessage()
	 */
	public String getRenderedMessage() {
		String renderedMessage = super.getRenderedMessage();
		// �����message�а���(')��������Ҫ����
		if (renderedMessage.indexOf("'") != -1)
			renderedMessage = renderedMessage.replaceAll("'", "''");
		return renderedMessage;
	}
}
