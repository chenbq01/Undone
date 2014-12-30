package com.beiwaiclass.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beiwaiclass.weixin.helper.AccessTokenHelper;

@Controller
public class AccessTokenController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String get() {
		return AccessTokenHelper.returnAccessToken();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, params = "refresh")
	@ResponseBody
	public String refresh() {
		AccessTokenHelper.requestAccessToken();
		return AccessTokenHelper.returnAccessToken();
	}

}
