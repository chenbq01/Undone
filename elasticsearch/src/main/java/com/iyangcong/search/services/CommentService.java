package com.iyangcong.search.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Comment;
import com.iyangcong.search.entities.SearchResult;

public interface CommentService {

	public long hitsByKeywords(String keywords);

	public Page<Comment> searchContentByKeywords(String keywords,
			Pageable pageable);

	public SearchResult<Comment> searchByKeywords(String keywords,
			Pageable pageable);

}
