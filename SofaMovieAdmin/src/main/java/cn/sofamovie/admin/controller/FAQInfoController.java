package cn.sofamovie.admin.controller;

import java.io.UnsupportedEncodingException;
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
@RequestMapping("/faq")
public class FAQInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(FAQInfoController.class);

	@Autowired
	private FAQInfoService faqInfoService;

	@Autowired
	private CategoryInfoService categoryInfoService;

	@RequestMapping(value = "/listfaq", method = RequestMethod.GET)
	public String manage(String categoryid, String categoryname, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问服务支持页--");
		}
		String strCategoryname = "";
		try {
			strCategoryname = java.net.URLDecoder.decode(categoryname, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.addAttribute("categoryid", categoryid);
		model.addAttribute("categoryname", strCategoryname);

		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", strCategoryname);
		return "/faq/list";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list(String categoryid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看服务指南[" + categoryid + "]对应的服务支持内容--");
		}
		List<FAQInfo> list = faqInfoService.findAllFAQsByCategoryID(categoryid);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST, params = "_json=true")
	public Object remove(HttpSession session, String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除服务支持内容--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = faqInfoService.removeFAQ(id);
		map.put("flag", String.valueOf(flag));
		generateKefuAndSousuoPage(session);
		return map;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, FAQInfo faqInfo,
			String categoryname, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--添加服务支持内容--");
		}
		FAQInfo info = faqInfoService.saveFAQInfo(faqInfo);
		if (info != null && info.getId() != null) {
			generateKefuAndSousuoPage(session);
			model.addAttribute("msg", "服务支持内容添加成功！");
		} else {
			model.addAttribute("msg", "服务支持内容添加失败！");
		}

		model.addAttribute("categoryid", faqInfo.getCategoryid());
		model.addAttribute("categoryname", categoryname);

		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", categoryname);
		return "/faq/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, FAQInfo faqInfo, String faqid,
			String categoryname, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--修改服务支持内容--");
		}
		boolean flag = faqInfoService.modifyFAQByID(faqid, faqInfo
				.getQuestion(), faqInfo.getAnswer(), faqInfo.getOrderno()
				.toString());
		if (flag) {
			generateKefuAndSousuoPage(session);
			model.addAttribute("msg", "服务支持内容修改成功！");
		} else {
			model.addAttribute("msg", "服务支持内容修改失败！");
		}

		model.addAttribute("categoryid", faqInfo.getCategoryid());
		model.addAttribute("categoryname", categoryname);

		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", categoryname);
		return "/faq/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getinfo", method = RequestMethod.POST, params = "_json=true")
	public Object getinfo(String faqid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看FAQ信息--");
		}
		FAQInfo faqInfo = faqInfoService.getinfo(faqid);
		if (faqInfo.getAnswer() == null) {
			faqInfo.setAnswer("");
		}
		if (faqInfo.getQuestion() == null) {
			faqInfo.setQuestion("");
		}
		if (faqInfo.getOrderno() == null) {
			faqInfo.setOrderno(0);
		}
		return faqInfo;
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
