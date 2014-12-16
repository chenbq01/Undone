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

import com.iyangcong.search.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/elasticsearch-context.xml" })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private String keywords;

	private Pageable pageable;

	@Before
	public void before() {
		keywords = "美丽英文";
		pageable = new PageRequest(0, 10);
	}

	@Test
	public void testSearchByKeywords() {

		Page<User> userList = userRepository.searchContentByKeywords(keywords, pageable);

		for (User user : userList) {
			System.out.println(user);
		}

	}

	@Test
	public void testHitsByKeywords() {

		long hits = userRepository.hitsByKeywords(keywords);

		System.out.println(hits);

	}

}
