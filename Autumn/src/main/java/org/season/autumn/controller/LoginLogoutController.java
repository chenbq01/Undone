package org.season.autumn.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogoutController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String home(Locale locale) {
		logger.info("Welcome login! The client locale is {}.", locale);
		return "login";
	}

}