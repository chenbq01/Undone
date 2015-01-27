package org.season.autumn.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.season.autumn.security.LoginQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogoutController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String home(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		// 从预定义的诗句中,随机挑一个上句
		Random rnd = new Random();
		int questionId = rnd.nextInt(LoginQuestion.getQuestions().size());
		model.addAttribute("questionId", questionId);
		model.addAttribute("question",
				LoginQuestion.getQuestions().get(questionId).split("/")[0]);
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accessdenied")
	public String accessDenied(Locale locale, Model model, HttpServletRequest request) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		/*
		 * AccessDeniedException ex = (AccessDeniedException) request
		 * .getAttribute
		 * (AccessDeniedHandlerImpl.SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY
		 * );
		 */
		return "accessdenied";
	}

}
