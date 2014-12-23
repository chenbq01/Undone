package cn.com.uwoa.business.mobile.web.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.uwoa.business.mobile.service.IDishesService;
import cn.com.uwoa.business.mobile.service.IPartnerService;
import cn.com.uwoa.global.exception.ControllerException;
import cn.com.uwoa.global.util.PartnerProfile;

/**
 * Handles requests for the application home page.
 */
@Controller("mobileHomeController")
@RequestMapping("/mobile")
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private IPartnerService partnerService;

	@Autowired
	private IDishesService dishesService;

	@RequestMapping(value = "/{partnercode}", method = RequestMethod.GET)
	public String toPartnerHomePage(
			@PathVariable("partnercode") String partnercode,
			HttpSession session, Locale locale, Model model) {

		if (!partnerService.isExistedPartnerCode(partnercode)) {
			throw new ControllerException("请求域名/地址有误！");
		}

		Map<String, Object> profile = partnerService
				.getPartnerProfileByCode(partnercode);
		if (profile == null) {
			throw new ControllerException("无法获取餐厅信息！");
		}

		session.setAttribute("_profile", profile);
		String partnerid = PartnerProfile.getPartnerID(session);

		List<Map<String, Object>> buffetlist = dishesService.getBuffetList(
				partnercode, partnerid);
		model.addAttribute("buffetlist", buffetlist);

		return "/business/home";

	}

	@RequestMapping(value = "/{partnercode}", params = "shadow", method = RequestMethod.GET)
	public String toPartnerHomeShadowPage(
			@PathVariable("partnercode") String partnercode,
			HttpSession session, Locale locale, Model model) {

		if (session.getAttribute("_profile") == null) {
			throw new ControllerException("请求域名/地址有误！");
		}

		String partnerid = PartnerProfile.getPartnerID(session);

		List<Map<String, Object>> buffetlist = dishesService.getBuffetList(
				partnercode, partnerid);
		model.addAttribute("buffetlist", buffetlist);

		return "/business/homeshadow";

	}

	@RequestMapping(value = "/preview/{partnercode}", method = RequestMethod.GET)
	public String toPreviewPartnerHomePage(
			@PathVariable("partnercode") String partnercode,
			HttpSession session, Locale locale, Model model) {

		if (!partnerService.isExistedPartnerCode(partnercode)) {
			throw new ControllerException("请求域名/地址有误！");
		}

		Map<String, Object> profile = partnerService
				.getPartnerProfileByCode(partnercode);
		if (profile == null) {
			throw new ControllerException("无法获取餐厅信息！");
		}

		session.setAttribute("_profile", profile);

		return "/business/home";

	}
	
	@RequestMapping(value = "/pop/{partnercode}", method = RequestMethod.GET)
	public String toPopPage(
			@PathVariable("partnercode") String partnercode,
			HttpSession session, Locale locale, Model model) {

		if (!partnerService.isExistedPartnerCode(partnercode)) {
			throw new ControllerException("请求域名/地址有误！");
		}

		Map<String, Object> profile = partnerService
				.getPartnerProfileByCode(partnercode);
		if (profile == null) {
			throw new ControllerException("无法获取餐厅信息！");
		}

		session.setAttribute("_profile", profile);

		return "/business/pop";

	}
}
