package com.beiwaiclass.weixin.helper;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RequestBodyHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(RequestBodyHelper.class);

	private RequestBodyHelper() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, String> resolveRequestBody(String requestbody) {
		final Map<String, String> kvmessage = new HashMap<String, String>();
		try {

			final SAXReader reader = new SAXReader();
			Document document = reader.read(new StringReader(requestbody));

			if (null == document)
				return kvmessage;

			Element root = document.getRootElement();

			if (null == root)
				return kvmessage;

			@SuppressWarnings("unchecked")
			Iterator<Element> iterator = root.elementIterator();
			if (null != iterator) {
				while (iterator.hasNext()) {
					Element element = iterator.next();
					if (null == element)
						continue;
					kvmessage.put(element.getName(), element.getStringValue());
				}
			}

			return kvmessage;
		} catch (DocumentException e) {
			if (logger.isErrorEnabled()) {
				logger.error("解析微信转发的POST请求出错", e);
			}
			return kvmessage;
		}
	}

}
