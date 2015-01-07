package com.beiwaiclass.weixin.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beiwaiclass.weixin.config.MsgTypeConfig;
import com.beiwaiclass.weixin.service.MessageHandleService;
import com.beiwaiclass.weixin.service.ResponseBuilderService;

@Service
public class DefaultMessageHandleService implements MessageHandleService {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultMessageHandleService.class);

	@Resource(name = "msgtypehandler")
	private Map<String, ResponseBuilderService> msgtypehandler;

	@Override
	public String handle(Map<String, String> params) {
		String msgtype = params.get("MsgType");
		String event = params.get("Event");

		if (msgtype == null) {
			if (logger.isErrorEnabled()) {
				logger.error("解析微信转发的POST请求信息中不包括MsgType");
			}
			return "";
		}

		String key = msgtype;
		if (MsgTypeConfig.EVENT.equals(msgtype) && event != null) {
			key = key + "_" + event;
		}

		ResponseBuilderService service = msgtypehandler.get(key);
		if (service == null) {
			if (logger.isErrorEnabled()) {
				logger.error("未注册与[MsgType:" + msgtype + ",Event:" + event
						+ "]对应的处理类");
			}
			return "";
		}
		return service.buildResponse(params);
	}
}
