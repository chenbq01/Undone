package org.season.autumn.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	public CustomAuthenticationToken(String principal, String credentials,
			Integer questionId, String answer) {
		super(principal, credentials);
		this.answer = answer;
		this.question = questionId;
	}

	private String answer;
	private Integer question;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getQuestionId() {
		return question;
	}

	public void setQuestionId(Integer questionId) {
		this.question = questionId;
	}

}
