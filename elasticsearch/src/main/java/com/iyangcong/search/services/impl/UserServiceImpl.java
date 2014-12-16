package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.entities.User;
import com.iyangcong.search.repositories.UserRepository;
import com.iyangcong.search.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return userRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<User> searchContentByKeywords(String keywords, Pageable pageable) {
		return userRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<User> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<User> users = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<User> searchResult = new SearchResult<User>();
		if (users != null) {
			searchResult.setContent(users.getContent());
		} else {
			searchResult.setContent(new ArrayList<User>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
