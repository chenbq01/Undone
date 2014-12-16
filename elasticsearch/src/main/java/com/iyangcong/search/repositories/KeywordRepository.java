package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Keyword;

public interface KeywordRepository {

	public long hitsByKeywords(String keywords);

	public Page<Keyword> searchContentByKeywords(String keywords, Pageable pageable);

}
