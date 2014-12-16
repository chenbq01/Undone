package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.OpenClassSegment;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.OpenClassSegmentRepository;
import com.iyangcong.search.services.OpenClassSegmentService;

@Service
public class OpenClassSegmentServiceImpl implements OpenClassSegmentService {

	@Autowired
	private OpenClassSegmentRepository openClassSegmentRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return openClassSegmentRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<OpenClassSegment> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return openClassSegmentRepository.searchContentByKeywords(keywords,
				pageable);
	}

	@Override
	public SearchResult<OpenClassSegment> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<OpenClassSegment> openclasssegments = searchContentByKeywords(
				keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<OpenClassSegment> searchResult = new SearchResult<OpenClassSegment>();
		if (openclasssegments != null) {
			searchResult.setContent(openclasssegments.getContent());
		} else {
			searchResult.setContent(new ArrayList<OpenClassSegment>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
