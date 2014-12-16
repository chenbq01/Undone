package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Segment;

public interface SegmentRepository {

	public long hitsByKeywords(String keywords);

	public Page<Segment> searchContentByKeywords(String keywords, Pageable pageable);

}
