package com.iyangcong.search.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iyangcong.search.entities.Comment;
import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.repositories.CommentRepository;
import com.iyangcong.search.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public long hitsByKeywords(String keywords) {
		return commentRepository.hitsByKeywords(keywords);
	}

	@Override
	public Page<Comment> searchContentByKeywords(String keywords,
			Pageable pageable) {
		return commentRepository.searchContentByKeywords(keywords, pageable);
	}

	@Override
	public SearchResult<Comment> searchByKeywords(String keywords,
			Pageable pageable) {
		Page<Comment> comments = searchContentByKeywords(keywords, pageable);

		long hits = hitsByKeywords(keywords);

		SearchResult<Comment> searchResult = new SearchResult<Comment>();
		if (comments != null) {
			searchResult.setContent(comments.getContent());
		} else {
			searchResult.setContent(new ArrayList<Comment>());
		}
		searchResult.setPage(pageable.getPageSize());
		searchResult.setSize(pageable.getPageNumber());
		searchResult.setTotalElements(hits);
		searchResult.setTotalPages(Double.valueOf(
				Math.ceil(hits / pageable.getPageSize())).intValue());

		return searchResult;
	}

}
