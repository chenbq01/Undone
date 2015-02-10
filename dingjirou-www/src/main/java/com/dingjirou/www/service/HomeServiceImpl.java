package com.dingjirou.www.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public String home(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		return dateFormat.format(date);
	}

}
