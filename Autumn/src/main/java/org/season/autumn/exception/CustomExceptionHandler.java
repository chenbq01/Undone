package org.season.autumn.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		LoggerFactory.getLogger(ex.getClass()).error(ex.getMessage(), ex);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);

		return new ModelAndView("/global/exceptionhandler", model);
	}

}
