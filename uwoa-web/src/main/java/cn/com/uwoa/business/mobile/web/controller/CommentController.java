package cn.com.uwoa.business.mobile.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mobileCommentController")
@RequestMapping("/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	@RequestMapping(value = "/{commentid}", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public String toSpecialtyListPage(
			@PathVariable("commentid") String commentid, HttpSession session,
			Model model) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute("profile");
		logger.info(profile.get("code").toString());
		// 从solr中获取特色菜列表

		return "/business/comment/info";
	}
}
