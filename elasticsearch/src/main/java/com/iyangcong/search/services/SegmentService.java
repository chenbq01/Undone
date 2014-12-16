package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.Segment;

public interface SegmentService {

	public long hitsByKeywords(String keywords);

	public Page<Segment> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Segment> searchByKeywords(String keywords,
			Pageable pageable);

}
