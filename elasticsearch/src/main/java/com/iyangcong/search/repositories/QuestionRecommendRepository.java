package com.iyangcong.search.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iyangcong.search.entities.QuestionRecommend;

public interface QuestionRecommendRepository {

	public long hitsByKeywords(String keywords);

	public Page<QuestionRecommend> searchContentByKeywords(String keywords, Pageable pageable);

}
