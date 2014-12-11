package cn.sofamovie.admin.controller;

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

import cn.sofamovie.admin.domain.RegionInfo;
import cn.sofamovie.admin.service.BoxImageInfoService;
import cn.sofamovie.admin.service.BoxInfoService;
import cn.sofamovie.admin.service.CardImageInfoService;
import cn.sofamovie.admin.service.CardInfoService;
import cn.sofamovie.admin.service.RegionInfoService;
import cn.sofamovie.admin.util.StaticHtmlHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/region")
public class RegionInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegionInfoController.class);

	@Autowired
	private RegionInfoService regionInfoService;

	@Autowired
	private CardInfoService cardInfoService;

	@Autowired
	private CardImageInfoService cardImageInfoService;

	@Autowired
	private BoxInfoService boxInfoService;

	@Autowired
	private BoxImageInfoService boxImageInfoService;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(HttpSession session, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问区域页--");
		}
		model.addAttribute("_ACTIVED_MENU", "3");
		model.addAttribute("_MENU_TITLE", "区域");
		return "/region/manage";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看所有区域--");
		}
		List<RegionInfo> list = regionInfoService.findAllRegions();
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/getinfo", method = RequestMethod.POST, params = "_json=true")
	public Object getinfo(String regionid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看区域信息--");
		}
		RegionInfo regionInfo = regionInfoService.getinfo(regionid);
		if (regionInfo.getGuidechannels() == null) {
			regionInfo.setGuidechannels("");
		}
		if (regionInfo.getDemandchannels() == null) {
			regionInfo.setDemandchannels("");
		}
		if (regionInfo.getSupportphone() == null) {
			regionInfo.setSupportphone("");
		}
		return regionInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "_json=true")
	public Object addRegion(HttpSession session, String regionname) {
		if (logger.isDebugEnabled()) {
			logger.debug("--添加区域--");
		}
		String flag = null;
		Map<String, String> map = new HashMap<String, String>();
		RegionInfo regionInfo = regionInfoService.addRegion(regionname);
		if (regionInfo != null && regionInfo.getId() != null) {
			flag = "true";
			generateZhinanPage(session);
		} else {
			flag = "false";
		}

		map.put("flag", flag);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/setchannel", method = RequestMethod.POST, params = "_json=true")
	public Object setchannel(HttpSession session, String regionid,
			String guidechannels, String demandchannels) {
		if (logger.isDebugEnabled()) {
			logger.debug("--设置频道--");
		}

		Map<String, String> map = new HashMap<String, String>();
		boolean flag = regionInfoService.setChannel(regionid, guidechannels,
				demandchannels);
		generateZhinanPage(session);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/setsupportphone", method = RequestMethod.POST, params = "_json=true")
	public Object setsupportphone(HttpSession session, String regionid,
			String supportphone) {
		if (logger.isDebugEnabled()) {
			logger.debug("--设置支持电话--");
		}

		Map<String, String> map = new HashMap<String, String>();
		boolean flag = regionInfoService
				.setSupportPhone(regionid, supportphone);
		generateZhinanPage(session);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST, params = "_json=true")
	public Object remove(HttpSession session, String regionid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除区域--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = regionInfoService.removeRegion(regionid);
		map.put("flag", String.valueOf(flag));
		generateZhinanPage(session);
		return map;
	}

	private void generateZhinanPage(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("regions", regionInfoService.findAllRegions());
		data.put("boxes", boxInfoService.findAllBoxes());
		data.put("cards", cardInfoService.findAllCards());
		data.put("boximages", boxImageInfoService.findAllImages());
		data.put("cardimages", cardImageInfoService.findAllImages());
		StaticHtmlHelper.createZhinanPage(session.getServletContext(), data);
	}

}
