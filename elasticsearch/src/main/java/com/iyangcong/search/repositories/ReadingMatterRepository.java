package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.ReadingMatter;

public interface ReadingMatterRepository {

	public long hitsByKeywords(String keywords);

	public Page<ReadingMatter> searchContentByKeywords(String keywords, Pageable pageable);

}
