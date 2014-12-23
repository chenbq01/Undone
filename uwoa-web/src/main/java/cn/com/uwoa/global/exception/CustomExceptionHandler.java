package cn.com.uwoa.global.exception;

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

		if (ex instanceof DaoException) {
			return new ModelAndView("/global/exception/dao", model);
		} else if (ex instanceof ServiceException) {
			return new ModelAndView("/global/exception/service", model);
		} else if (ex instanceof ControllerException) {
			return new ModelAndView("/global/exception/controller", model);
		} else {
			return new ModelAndView("/global/exception/default", model);
		}

	}
}
