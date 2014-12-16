package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Group;
import com.iyangcong.search.entities.SearchResult;

public interface GroupService {

	public long hitsByKeywords(String keywords);

	public Page<Group> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Group> searchByKeywords(String keywords,
			Pageable pageable);

}
