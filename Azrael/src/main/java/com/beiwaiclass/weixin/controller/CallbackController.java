package com.beiwaiclass.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beiwaiclass.weixin.helper.CallbackHelper;

@Controller
@RequestMapping(value = "/callback")
public class CallbackController {

	private static final Logger logger = LoggerFactory
			.getLogger(CallbackController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public String verify(String signature, String timestamp, String nonce,
			String echostr) {
		logger.info("signature:" + signature);
		logger.info("timestamp:" + timestamp);
		logger.info("nonce:" + nonce);
		logger.info("echostr:" + echostr);
		if (CallbackHelper.validCallbackInfo(signature, timestamp, nonce)) {
			return echostr;
		}
		throw new SecurityException("请求非来源于微信");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	@ResponseBody
	public String receive(String signature, String timestamp, String nonce,
			@RequestBody String requestBody) {
		logger.info("signature:" + signature);
		logger.info("timestamp:" + timestamp);
		logger.info("nonce:" + nonce);
		logger.info("requestBody:" + requestBody);
		if (!CallbackHelper.validCallbackInfo(signature, timestamp, nonce)) {
			throw new SecurityException("请求非来源于微信");
		}
		return "";
	}

}
