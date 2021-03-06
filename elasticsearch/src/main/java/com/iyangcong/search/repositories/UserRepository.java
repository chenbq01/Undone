package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.User;

public interface UserRepository {

	public long hitsByKeywords(String keywords);

	public Page<User> searchContentByKeywords(String keywords, Pageable pageable);

}
