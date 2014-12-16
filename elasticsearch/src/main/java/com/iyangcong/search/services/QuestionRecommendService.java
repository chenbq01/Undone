package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.QuestionRecommend;
import com.iyangcong.search.entities.SearchResult;

public interface QuestionRecommendService {

	public long hitsByKeywords(String keywords);

	public Page<QuestionRecommend> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<QuestionRecommend> searchByKeywords(String keywords,
			Pageable pageable);

}
