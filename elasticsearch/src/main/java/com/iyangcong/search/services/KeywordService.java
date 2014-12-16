package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Keyword;
import com.iyangcong.search.entities.SearchResult;

public interface KeywordService {

	public long hitsByKeywords(String keywords);

	public Page<Keyword> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Keyword> searchByKeywords(String keywords,
			Pageable pageable);

}
