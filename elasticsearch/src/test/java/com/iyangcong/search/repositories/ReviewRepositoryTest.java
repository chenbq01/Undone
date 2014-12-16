package com.iyangcong.search.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iyangcong.search.entities.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/elasticsearch-context.xml" })
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepository;

	private String keywords;

	private Pageable pageable;

	@Before
	public void before() {
		keywords = "美丽英文";
		pageable = new PageRequest(0, 10);
	}

	@Test
	public void testSearchByKeywords() {

		Page<Review> reviewList = reviewRepository.searchContentByKeywords(keywords, pageable);

		for (Review review : reviewList) {
			System.out.println(review);
		}

	}

	@Test
	public void testHitsByKeywords() {

		long hits = reviewRepository.hitsByKeywords(keywords);

		System.out.println(hits);

	}

}
