package org.season.autumn.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.season.autumn.springsecurity.security.IChangePassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

	@Autowired
	private IChangePassword changePasswordDao;

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
			@RequestParam("password") String newPassword) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = principal.toString();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		changePasswordDao.changePassword(username, newPassword);
		SecurityContextHolder.clearContext();
		return "redirect:/account/home";
	}

}
