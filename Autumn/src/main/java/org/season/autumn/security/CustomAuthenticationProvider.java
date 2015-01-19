package org.season.autumn.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomAuthenticationProvider.class);

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// 转换为自定义的token
		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String poem = LoginQuestion.getQuestions().get(token.getQuestionId());
		// 校验下一句的答案是否正确
		if (!poem.split("/")[1].equals(token.getAnswer())) {
			throw new BadAnswerException("the answer is wrong!");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		if (logger.isInfoEnabled()) {
			logger.info("用户[" + username + "]尝试通过白名单验证登录！");
		}
		String[] whiteLists = new String[] { "ADMIN", "SUPERVISOR" };

		// 如果用户在白名单里,直接放行(注:仅仅只是演示,千万不要在实际项目中这么干!)
		if (Arrays.asList(whiteLists).contains(username)) {
			if (logger.isInfoEnabled()) {
				logger.info("用户[" + username + "]通过白名单无密码登录！");
			}
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			UserDetails user = new User(username, "whatever", authorities);
			return user;
		}

		// return new User(username, "no-password", false, false, false, false,
		// new ArrayList<GrantedAuthority>());
		throw new UsernameNotFoundException("用户[" + username + "]不在白名单中");
	}

}
