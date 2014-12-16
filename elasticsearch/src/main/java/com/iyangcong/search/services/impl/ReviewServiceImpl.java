package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.Review;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.ReviewRepository;
import com.iyangcong.search.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return reviewRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Review> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return reviewRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Review> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Review> reviews = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Review> searchResult = new SearchResult<Review>();
		if (reviews != null) {
			searchResult.setContent(reviews.getContent());
		} else {
			searchResult.setContent(new ArrayList<Review>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
