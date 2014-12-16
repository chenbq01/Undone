package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Review;
import com.iyangcong.search.entities.SearchResult;

public interface ReviewService {

	public long hitsByKeywords(String keywords);

	public Page<Review> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Review> searchByKeywords(String keywords,
			Pageable pageable);

}
