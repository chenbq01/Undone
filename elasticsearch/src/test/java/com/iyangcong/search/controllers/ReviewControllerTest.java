package com.iyangcong.search.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/elasticsearch-context.xml" })
public class ReviewControllerTest {

	private Map<String, String> params;

	@Before
	public void before() {
		params = new HashMap<String, String>();
		params.put("keywords", "美丽英文");
		params.put("page", "0");
		params.put("size", "10");
	}

	@Test
	public void testSearch() {
		try {
			String responseBody = Jsoup
					.connect("http://localhost:8080/search/review/search")
					.ignoreContentType(true).timeout(50000).data(params)
					.method(Method.POST).execute().body();
			JSONObject obj = JSONObject.fromObject(responseBody);
			System.out.println(obj.getString("content"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
