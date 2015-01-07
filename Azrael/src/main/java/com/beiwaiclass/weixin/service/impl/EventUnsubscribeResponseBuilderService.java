package com.beiwaiclass.weixin.service.impl;

import java.util.Map;

import net.sf.json.JSONArray;

import com.beiwaiclass.weixin.helper.ResponseBodyHelper;
import com.beiwaiclass.weixin.service.ResponseBuilderService;

public class EventUnsubscribeResponseBuilderService implements
		ResponseBuilderService {
	@Override
	public String buildResponse(Map<String, String> params) {
		return ResponseBodyHelper.buildMsgTextResponseXML(params
				.get("FromUserName"), params.get("ToUserName"), JSONArray
				.fromObject(params).toString());
	}
}
