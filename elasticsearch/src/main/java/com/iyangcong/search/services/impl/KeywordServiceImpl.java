package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.Keyword;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.KeywordRepository;
import com.iyangcong.search.services.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	private KeywordRepository keywordRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return keywordRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Keyword> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return keywordRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Keyword> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Keyword> keyword = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Keyword> searchResult = new SearchResult<Keyword>();
		if (keyword != null) {
			searchResult.setContent(keyword.getContent());
		} else {
			searchResult.setContent(new ArrayList<Keyword>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
