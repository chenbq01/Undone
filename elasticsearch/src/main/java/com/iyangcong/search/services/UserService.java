package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.User;

public interface UserService {

	public long hitsByKeywords(String keywords);

	public Page<User> searchContentByKeywords(String keywords, Pageable pageable);

	public SearchResult<User> searchByKeywords(String keywords,
			Pageable pageable);

}
