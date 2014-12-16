package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.Segment;
import com.iyangcong.search.repositories.SegmentRepository;
import com.iyangcong.search.services.SegmentService;

@Service
public class SegmentServiceImpl implements SegmentService {

	@Autowired
	private SegmentRepository commentRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return commentRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Segment> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return commentRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Segment> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Segment> segments = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Segment> searchResult = new SearchResult<Segment>();
		if (segments != null) {
			searchResult.setContent(segments.getContent());
		} else {
			searchResult.setContent(new ArrayList<Segment>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
