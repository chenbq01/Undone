package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.Group;

public interface GroupRepository {

	public long hitsByKeywords(String keywords);

	public Page<Group> searchContentByKeywords(String keywords, Pageable pageable);

}
