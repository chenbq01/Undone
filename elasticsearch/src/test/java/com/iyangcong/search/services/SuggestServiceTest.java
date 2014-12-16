package com.iyangcong.search.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iyangcong.search.services.SuggestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/elasticsearch-context.xml" })
public class SuggestServiceTest {

	@Autowired
	private SuggestService suggestService;

	private String keywords;

	@Before
	public void before() {
		keywords = "美丽英文";
	}

	@Test
	public void testGetSuggestByKeywords() {

		List<String> suggests = suggestService.getSuggestByKeywords(keywords);

		for (String suggest : suggests) {
			System.out.println(suggest);
		}

	}

}
