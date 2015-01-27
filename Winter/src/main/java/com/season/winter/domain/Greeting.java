package com.season.winter.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends ResourceSupport {

	@JsonProperty("_content")
	private String content;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}