package com.iyangcong.search.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iyangcong.search.entities.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/elasticsearch-context.xml" })
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;

	private String keywords;

	private Pageable pageable;

	@Before
	public void before() {
		keywords = "美丽英文";
		pageable = new PageRequest(0, 10);
	}

	@Test
	public void testSearchContentByKeywords() {

		Page<Group> groupList = groupService.searchContentByKeywords(keywords,
				pageable);

		for (Group group : groupList) {
			System.out.println(group);
		}

	}

	@Test
	public void testHitsByKeywords() {

		long hits = groupService.hitsByKeywords(keywords);

		System.out.println(hits);

	}

	@Test
	public void testSearchByKeywords() {

		System.out.println(groupService.searchByKeywords(keywords, pageable));

	}

}
