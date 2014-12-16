package com.iyangcong.search.services;

import java.util.List;

public interface SuggestService {
	
	public List<String> getSuggestByKeywords(String keywords);

}
