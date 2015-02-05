package com.dingjirou.www.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class LoginLogoutController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogoutController.class);

	public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

	@Autowired
	private ImageCaptchaService captchaService;

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String toLogin(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accessdenied")
	public String accessDenied(Locale locale, Model model,
			HttpServletRequest request) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "accessdenied";
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void generateAndRenderCaptcha(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {

		byte[] captchaChallengeAsJpeg = null;

		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {

			String captchaId = request.getSession().getId();
			BufferedImage challenge = captchaService.getImageChallengeForID(
					captchaId, locale);

			ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
		} catch (IllegalArgumentException e) {
			try {
				if (logger.isErrorEnabled()) {
					logger.error(e.getMessage(), e);
				}
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ioe) {
				if (logger.isErrorEnabled()) {
					logger.error(ioe.getMessage(), ioe);
				}
			}
			return;
		} catch (CaptchaServiceException e) {
			try {
				if (logger.isErrorEnabled()) {
					logger.error(e.getMessage(), e);
				}
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException ioe) {
				if (logger.isErrorEnabled()) {
					logger.error(ioe.getMessage(), ioe);
				}
			}
			return;
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		}

		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/" + CAPTCHA_IMAGE_FORMAT);

		try {
			ServletOutputStream responseOutputStream = response
					.getOutputStream();
			responseOutputStream.write(captchaChallengeAsJpeg);
			responseOutputStream.flush();
			responseOutputStream.close();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		}

	}

}
