package cn.sofamovie.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sofamovie.admin.domain.CategoryInfo;
import cn.sofamovie.admin.domain.FAQInfo;
import cn.sofamovie.admin.service.CategoryInfoService;
import cn.sofamovie.admin.service.FAQInfoService;
import cn.sofamovie.admin.util.StaticHtmlHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/category")
public class CategoryInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(CategoryInfoController.class);

	@Autowired
	private CategoryInfoService categoryInfoService;

	@Autowired
	private FAQInfoService faqInfoService;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问客户支持分类页--");
		}
		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", "使用指南");
		return "/category/manage";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看所有使用指南--");
		}
		List<CategoryInfo> list = categoryInfoService.findAllCategories();
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/getinfo", method = RequestMethod.POST, params = "_json=true")
	public Object getinfo(String categoryid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看使用指南--");
		}
		CategoryInfo CategoryInfo = categoryInfoService.getinfo(categoryid);
		return CategoryInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "_json=true")
	public Object addCategory(HttpSession session, String categoryname,
			String orderno) {
		if (logger.isDebugEnabled()) {
			logger.debug("--添加使用指南--");
		}
		String flag = null;
		Map<String, String> map = new HashMap<String, String>();
		CategoryInfo categoryInfo = categoryInfoService.addCategory(
				categoryname, orderno);
		if (categoryInfo != null && categoryInfo.getId() != null) {
			flag = "true";
		} else {
			flag = "false";
		}
		map.put("flag", flag);
		generateKefuAndSousuoPage(session);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/setcategory", method = RequestMethod.POST, params = "_json=true")
	public Object setcategory(HttpSession session, String categoryid,
			String categoryname, String orderno) {
		if (logger.isDebugEnabled()) {
			logger.debug("--设置使用指南--");
		}

		Map<String, String> map = new HashMap<String, String>();
		boolean flag = categoryInfoService.setCategory(categoryid,
				categoryname, orderno);
		map.put("flag", String.valueOf(flag));
		generateKefuAndSousuoPage(session);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST, params = "_json=true")
	public Object remove(HttpSession session, String categoryid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除使用指南--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = categoryInfoService.removeCategory(categoryid);
		map.put("flag", String.valueOf(flag));
		generateKefuAndSousuoPage(session);
		return map;
	}

	private void generateKefuAndSousuoPage(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<CategoryInfo> list = categoryInfoService.findAllCategories();
		List<List<FAQInfo>> faqs = new ArrayList<List<FAQInfo>>();
		data.put("categories", list);
		for (int i = 0; i < list.size(); i++) {
			faqs.add(faqInfoService.findAllFAQsByCategoryID(list.get(i).getId()
					.toString()));
		}
		data.put("faqs", faqs);
		StaticHtmlHelper.createKefuPage(session.getServletContext(), data);
		StaticHtmlHelper.createSousuoPage(session.getServletContext(), data);
	}

}
