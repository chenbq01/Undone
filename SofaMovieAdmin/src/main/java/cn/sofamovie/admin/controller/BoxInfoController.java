package cn.sofamovie.admin.controller;

import java.io.UnsupportedEncodingException;
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

import cn.sofamovie.admin.domain.BoxInfo;
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
@RequestMapping("/box")
public class BoxInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(BoxInfoController.class);

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
	public String manage(String regionid, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问机顶盒页--");
		}
		model.addAttribute("regionid", regionid);
		model.addAttribute("_ACTIVED_MENU", "3");
		model.addAttribute("_MENU_TITLE", "机顶盒");
		return "/box/manage";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list(String regionid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看区域[" + regionid + "]对应的机顶盒--");
		}
		List<BoxInfo> list = boxInfoService.findAllBoxesByRegionID(regionid);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "_json=true")
	public Object addBox(HttpSession session, String regionid, String boxname) {
		if (logger.isDebugEnabled()) {
			logger.debug("--添加机顶盒--");
		}
		String flag = null;
		Map<String, String> map = new HashMap<String, String>();
		BoxInfo boxInfo = boxInfoService.addBoxByRegionID(boxname, regionid);
		if (boxInfo != null && boxInfo.getId() != null) {
			generateZhinanPage(session);
			flag = "true";
		} else {
			flag = "false";
		}
		map.put("flag", flag);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST, params = "_json=true")
	public Object remove(HttpSession session, String boxid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除机顶盒--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = boxInfoService.removeBox(boxid);
		generateZhinanPage(session);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@RequestMapping(value = "/imagelist", method = RequestMethod.GET)
	public String imagelist(String boxid, String boxname, String regionid,
			Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问机顶盒图片页--");
		}
		String strBoxName = "";
		try {
			strBoxName = java.net.URLDecoder.decode(boxname, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.addAttribute("boxid", boxid);
		model.addAttribute("regionid", regionid);
		model.addAttribute("boxname", strBoxName);

		model.addAttribute("_ACTIVED_MENU", "3");
		model.addAttribute("_MENU_TITLE", strBoxName);

		return "/box/imagelist";
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
