package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.Topic;

public interface TopicService {

	public long hitsByKeywords(String keywords);

	public Page<Topic> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Topic> searchByKeywords(String keywords,
			Pageable pageable);

}
