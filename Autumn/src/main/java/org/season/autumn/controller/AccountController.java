package org.season.autumn.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "/account/home";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage() {
		return "/account/changePassword";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String submitChangePasswordPage(
			@RequestParam("oldpassword") String oldPassword,
			@RequestParam("newpassword") String newPassword) {

		SecurityContextHolder.clearContext();
		return "redirect:/account/home";
	}

}
