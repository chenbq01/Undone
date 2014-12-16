package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Comment;

public interface CommentRepository {

	public long hitsByKeywords(String keywords);

	public Page<Comment> searchContentByKeywords(String keywords, Pageable pageable);

}
