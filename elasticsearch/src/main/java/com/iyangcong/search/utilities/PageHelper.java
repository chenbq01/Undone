package com.iyangcong.search.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageHelper {

	private final static int DEFAULT_PAGE_NO = 0;

	private final static int DEFAULT_PAGE_SIZE = 10;

	private static int getPageNo(String page) {
		int nPage = DEFAULT_PAGE_NO;
		if (StringHelper.isNumeric(page)) {
			nPage = Integer.parseInt(page);
		}
		return nPage;
	}

	private static int getPageSize(String size) {
		int nSize = DEFAULT_PAGE_SIZE;
		if (StringHelper.isNumeric(size) && Integer.parseInt(size) > 0) {
			nSize = Integer.parseInt(size);
		}
		return nSize;
	}

	public static Pageable generatePageable(String page, String size) {
		return new PageRequest(getPageNo(page), getPageSize(size));
	}

}
