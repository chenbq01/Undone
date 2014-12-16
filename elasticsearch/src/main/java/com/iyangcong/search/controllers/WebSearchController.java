package com.iyangcong.search.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iyangcong.search.entities.SearchResult;
import com.iyangcong.search.services.CommentService;
import com.iyangcong.search.services.GroupService;
import com.iyangcong.search.services.ReadingMatterService;
import com.iyangcong.search.services.ReviewService;
import com.iyangcong.search.services.TopicService;
import com.iyangcong.search.services.UserService;
import com.iyangcong.search.utilities.PageHelper;

@Controller
@RequestMapping("/web")
public class WebSearchController {

	private static final Logger logger = LoggerFactory
			.getLogger(WebSearchController.class);

	@Autowired
	private ReadingMatterService readingMatterService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Object search(@RequestParam(value = "keywords") String keywords,
			@RequestParam(value = "page") String page,
			@RequestParam(value = "size", required = false) String size) {
		if (logger.isDebugEnabled()) {
			logger.debug("--search--");
		}

		Pageable pageable = PageHelper.generatePageable(page, size);

		Map<String, SearchResult<? extends Object>> searchResult = new HashMap<String, SearchResult<? extends Object>>();

		searchResult.put("readingmatter",
				readingMatterService.searchByKeywords(keywords, pageable));
		searchResult.put("review",
				reviewService.searchByKeywords(keywords, pageable));
		searchResult.put("comment",
				commentService.searchByKeywords(keywords, pageable));
		searchResult.put("topic",
				topicService.searchByKeywords(keywords, pageable));
		searchResult.put("user",
				userService.searchByKeywords(keywords, pageable));
		searchResult.put("group",
				groupService.searchByKeywords(keywords, pageable));

		return searchResult;
	}

}
