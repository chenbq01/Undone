package org.springframework.security.web.extension.captcha.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

import com.octo.captcha.service.image.ImageCaptchaService;

public class UsernamePasswordCaptchaAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory
			.getLogger(UsernamePasswordCaptchaAuthenticationFilter.class);

	public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "j_captcha";

	private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

	private ImageCaptchaService captchaService;

	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(captchaParameter);
	}

	public final String getCaptchaParameter() {
		return captchaParameter;
	}

	public void setCaptchaParameter(String captchaParameter) {
		Assert.hasText(captchaParameter,
				"Captcha parameter must not be empty or null");
		this.captchaParameter = captchaParameter;
	}

	public ImageCaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(ImageCaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		String captchaID = request.getSession().getId();  
		String requestCaptcha = obtainCaptcha(request);
		
		if (!getCaptchaService().validateResponseForID(captchaID, requestCaptcha)) {
			throw new CaptchaException("Verification code validation failed");
		}
		return super.attemptAuthentication(request, response);
	}

}
