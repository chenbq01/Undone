package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.QuestionRecommend;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.QuestionRecommendRepository;
import com.iyangcong.search.services.QuestionRecommendService;

@Service
public class QuestionRecommendServiceImpl implements QuestionRecommendService {

	@Autowired
	private QuestionRecommendRepository questionRecommendRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return questionRecommendRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<QuestionRecommend> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return questionRecommendRepository.searchContentByKeywords(keywords,
				pageable);
	}

	@Override
	public SearchResult<QuestionRecommend> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<QuestionRecommend> questionrecommends = searchContentByKeywords(
				keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<QuestionRecommend> searchResult = new SearchResult<QuestionRecommend>();
		if (questionrecommends != null) {
			searchResult.setContent(questionrecommends.getContent());
		} else {
			searchResult.setContent(new ArrayList<QuestionRecommend>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
