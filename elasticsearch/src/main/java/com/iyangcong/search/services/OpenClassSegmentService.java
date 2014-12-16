package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.OpenClassSegment;
import com.iyangcong.search.entities.SearchResult;

public interface OpenClassSegmentService {

	public long hitsByKeywords(String keywords);

	public Page<OpenClassSegment> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<OpenClassSegment> searchByKeywords(String keywords,
			Pageable pageable);

}
