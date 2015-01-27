package com.season.winter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.season.winter.domain.Greeting;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HttpEntity<Greeting> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		Greeting greeting = new Greeting();
		greeting.setContent(String.format(TEMPLATE, name));
		greeting.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeController.class).greeting(
						name)).withSelfRel());
		greeting.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeController.class).greeting(
						name)).withRel("where"));

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

	}

}
