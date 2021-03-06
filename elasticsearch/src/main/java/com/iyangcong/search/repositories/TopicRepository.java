package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Topic;

public interface TopicRepository {

	public long hitsByKeywords(String keywords);

	public Page<Topic> searchContentByKeywords(String keywords, Pageable pageable);

}
