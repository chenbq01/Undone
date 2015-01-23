package org.season.autumn.security.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.season.autumn.security.CustomAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

public class UsernamePasswordAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	// ~ Static fields/initializers
	// =====================================================================================

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
	public static final String SPRING_SECURITY_FORM_QUESTION_KEY = "j_question";
	public static final String SPRING_SECURITY_FORM_ANSWER_KEY = "j_answer";

	/**
	 * @deprecated If you want to retain the username, cache it in a customized
	 *             {@code AuthenticationFailureHandler}
	 */
	@Deprecated
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	private String questionParameter = SPRING_SECURITY_FORM_QUESTION_KEY;
	private String answerParameter = SPRING_SECURITY_FORM_ANSWER_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public UsernamePasswordAuthenticationFilter() {
		super("/j_spring_security_check");
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		// 解决中文诗句的post乱码问题
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		// 获取用户输入的下一句答案
		String answer = obtainAnswer(request);
		// 获取问题Id(即: hashTable的key)
		Integer question = obtainQuestion(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		// UsernamePasswordAuthenticationToken authRequest = new
		// UsernamePasswordAuthenticationToken(
		// username, password);

		// 这里将原来的UsernamePasswordAuthenticationToken换成我们自定义的CustomAuthenticationToken
		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(
				username, password, question, answer);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainAnswer(HttpServletRequest request) {
		return request.getParameter(answerParameter);
	}

	protected Integer obtainQuestion(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter(questionParameter));
	}

	public String getQuestionParameter() {
		return questionParameter;
	}

	public void setQuestionParameter(String questionParameter) {
		this.questionParameter = questionParameter;
	}

	public String getAnswerParameter() {
		return answerParameter;
	}

	public void setAnswerParameter(String answerParameter) {
		this.answerParameter = answerParameter;
	}

	/**
	 * Enables subclasses to override the composition of the password, such as
	 * by including additional values and a separator.
	 * <p>
	 * This might be used for example if a postcode/zipcode was required in
	 * addition to the password. A delimiter such as a pipe (|) should be used
	 * to separate the password and extended value(s). The
	 * <code>AuthenticationDao</code> will need to generate the expected
	 * password in a corresponding manner.
	 * </p>
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the password that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	/**
	 * Enables subclasses to override the composition of the username, such as
	 * by including additional values and a separator.
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the username that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the
	 * authentication request's details property.
	 *
	 * @param request
	 *            that an authentication request is being created for
	 * @param authRequest
	 *            the authentication request object that should have its details
	 *            set
	 */
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource
				.buildDetails(request));
	}

	/**
	 * Sets the parameter name which will be used to obtain the username from
	 * the login request.
	 *
	 * @param usernameParameter
	 *            the parameter name. Defaults to "j_username".
	 */
	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter,
				"Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	/**
	 * Sets the parameter name which will be used to obtain the password from
	 * the login request..
	 *
	 * @param passwordParameter
	 *            the parameter name. Defaults to "j_password".
	 */
	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter,
				"Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter.
	 * If set to true, and an authentication request is received which is not a
	 * POST request, an exception will be raised immediately and authentication
	 * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
	 * will be called as if handling a failed authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return usernameParameter;
	}

	public final String getPasswordParameter() {
		return passwordParameter;
	}

}
