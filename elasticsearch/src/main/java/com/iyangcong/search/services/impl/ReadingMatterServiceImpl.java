package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.ReadingMatter;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.ReadingMatterRepository;
import com.iyangcong.search.services.ReadingMatterService;

@Service
public class ReadingMatterServiceImpl implements ReadingMatterService {

	@Autowired
	private ReadingMatterRepository readingMatterRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return readingMatterRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<ReadingMatter> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return readingMatterRepository.searchContentByKeywords(keywords,
				pageable);
	}

	@Override
	public SearchResult<ReadingMatter> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<ReadingMatter> readingmatter = searchContentByKeywords(keywords,
				pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<ReadingMatter> searchResult = new SearchResult<ReadingMatter>();
		if (readingmatter != null) {
			searchResult.setContent(readingmatter.getContent());
		} else {
			searchResult.setContent(new ArrayList<ReadingMatter>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
