package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.Topic;
import com.iyangcong.search.repositories.TopicRepository;
import com.iyangcong.search.services.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return topicRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Topic> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return topicRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Topic> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Topic> topics = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Topic> searchResult = new SearchResult<Topic>();
		if (topics != null) {
			searchResult.setContent(topics.getContent());
		} else {
			searchResult.setContent(new ArrayList<Topic>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
