package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.OpenClassSegment;

public interface OpenClassSegmentRepository {

	public long hitsByKeywords(String keywords);

	public Page<OpenClassSegment> searchContentByKeywords(String keywords, Pageable pageable);

}
