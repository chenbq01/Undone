package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.Group;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.GroupRepository;
import com.iyangcong.search.services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return groupRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Group> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return groupRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Group> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Group> groups = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Group> searchResult = new SearchResult<Group>();
		if (groups != null) {
			searchResult.setContent(groups.getContent());
		} else {
			searchResult.setContent(new ArrayList<Group>());
		}
		searchResult.setPage(pageable.getPageNumber());
		searchResult.setSize(pageable.getPageSize());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
