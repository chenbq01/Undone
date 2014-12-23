package cn.com.uwoa.global.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class SecurityController {
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String getLoginPage() {

		logger.debug("Received request to show login page");

		return "/system/login";

	}

		
	


}
