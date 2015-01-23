package org.season.autumn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomAuthenticationProvider.class);

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
		if (logger.isInfoEnabled()) {
			logger.info("开始校验验证码");
		}
		// 转换为自定义的token
		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String poem = LoginQuestion.getQuestions().get(token.getQuestionId());
		// 校验下一句的答案是否正确
		if (!poem.split("/")[1].equals(token.getAnswer())) {
			throw new BadAnswerException("the answer is wrong!");
		}
		if (logger.isInfoEnabled()) {
			logger.info("验证码校验通过");
		}
	}

}
