package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.ReadingMatter;
import com.iyangcong.search.entities.SearchResult;

public interface ReadingMatterService {

	public long hitsByKeywords(String keywords);

	public Page<ReadingMatter> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<ReadingMatter> searchByKeywords(String keywords,
			Pageable pageable);

}
