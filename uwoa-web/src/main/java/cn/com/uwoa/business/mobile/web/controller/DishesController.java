package cn.com.uwoa.business.mobile.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.business.mobile.service.IDishesService;
import cn.com.uwoa.business.mobile.service.IOrderService;
import cn.com.uwoa.global.exception.ControllerException;
import cn.com.uwoa.global.util.OrderNo;
import cn.com.uwoa.global.util.PartnerProfile;

@Controller("mobileDishesController")
@RequestMapping("/dishes")
public class DishesController {

	private static final Logger logger = LoggerFactory
			.getLogger(DishesController.class);

	@Autowired
	private IDishesService dishesService;

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/specialtylist", method = RequestMethod.GET)
	public String toSpecialtyListPage(HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> specialtylist = dishesService
				.getSpecialtyList(partnercode, partnerid, buffetid);

		getOrderNum(specialtylist, (String) session.getAttribute("_orderno"),
				"id");
		model.addAttribute("specialtylist", specialtylist);
		model.addAttribute("ui-btn-active", "1");
		return "/business/dishes/specialtylist";
	}

	@RequestMapping(value = "/newlist", method = RequestMethod.GET)
	public String toNewListPage(HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> newlist = dishesService.getNewList(
				partnercode, partnerid, buffetid);

		getOrderNum(newlist, (String) session.getAttribute("_orderno"), "id");
		model.addAttribute("newlist", newlist);
		model.addAttribute("ui-btn-active", "2");
		return "/business/dishes/newlist";
	}

	@RequestMapping(value = "/promotionlist", method = RequestMethod.GET)
	public String toPromotionListPage(HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> promotionlist = dishesService
				.getPromotionList(partnercode, partnerid, buffetid);

		getOrderNum(promotionlist, (String) session.getAttribute("_orderno"),
				"id");
		model.addAttribute("promotionlist", promotionlist);
		model.addAttribute("ui-btn-active", "3");
		return "/business/dishes/promotionlist";
	}

	@RequestMapping(value = "/recommendlist", method = RequestMethod.GET)
	public String toRecommendListPage(HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> recommendlist = dishesService
				.getRecommendList(partnercode, partnerid, buffetid);

		getOrderNum(recommendlist, (String) session.getAttribute("_orderno"),
				"id");
		model.addAttribute("recommendlist", recommendlist);
		model.addAttribute("ui-btn-active", "4");
		return "/business/dishes/recommendlist";
	}

	@RequestMapping(value = "/menulist", method = RequestMethod.GET)
	public String toMenuListPage(HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> menulist = dishesService.getMenuList(
				partnercode, partnerid, buffetid);

		getOrderNum(menulist, (String) session.getAttribute("_orderno"), "id");
		model.addAttribute("menulist", menulist);
		model.addAttribute("ui-btn-active", "5");
		return "/business/dishes/menulist";
	}

	@RequestMapping(value = "/{dishesid}/commentlist", method = RequestMethod.GET)
	public String toDishesCommentListPage(
			@PathVariable("dishesid") String dishesid, HttpSession session,
			Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}
		List<Map<String, Object>> commentlist = dishesService
				.getCommentList(dishesid);
		;
		model.addAttribute("commentlist", commentlist);
		model.addAttribute("dishinfo", dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid));
		return "/business/dishes/commentlist";
	}

	@ResponseBody
	@RequestMapping(value = "/commentdata", method = RequestMethod.GET)
	public Object getDishesCommentData(
			@RequestParam("dishesid") String dishesid, HttpSession session,
			Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}
		List<Map<String, Object>> commentlist = dishesService
				.getCommentList(dishesid);
		;
		model.addAttribute("commentlist", commentlist);
		model.addAttribute("dishinfo", dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid));
		return model;
	}

	@RequestMapping(value = "/{dishesid}/prompt", method = RequestMethod.GET)
	public String toPromptPage(@PathVariable("dishesid") String dishesid,
			HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		Map<String, Object> dishinfo = dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid);
		model.addAttribute("dishinfo", dishinfo);

		if ("0".equals(dishinfo.get("is_weigh"))) {
			return "/business/dishes/add2orderwithnum";
		}
		return "/business/dishes/add2orderwithoutnum";
	}

	@RequestMapping(value = "/{dishesid}/modifyprompt", method = RequestMethod.GET)
	public String toModifyPromptPage(@PathVariable("dishesid") String dishesid,
			HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		Map<String, Object> dishinfo = dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid);
		model.addAttribute("dishinfo", dishinfo);

		if ("0".equals(dishinfo.get("is_weigh"))) {
			Integer num = orderService.getNumFromTemporaryOrder(
					(String) session.getAttribute("_orderno"), dishesid);
			model.addAttribute("num", num);
			return "/business/dishes/modify2orderwithnum";
		}
		return "/business/dishes/modify2orderwithoutnum";

	}

	@ResponseBody
	@RequestMapping(value = "/orderdishdata", method = RequestMethod.GET)
	public Object getOrderDishData(@RequestParam("dishesid") String dishesid,
			HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}

		Map<String, Object> dishinfo = dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid);
		model.addAttribute("dishinfo", dishinfo);

		if ("0".equals(dishinfo.get("is_weigh"))) {
			Integer num = orderService.getNumFromTemporaryOrder(
					(String) session.getAttribute("_orderno"), dishesid);
			model.addAttribute("num", num);
		}
		return model;
	}

	@RequestMapping(value = "/favouritelist/{mobileno}", method = RequestMethod.GET)
	public String toFavouritelistListPage(
			@PathVariable("mobileno") String mobileno, HttpSession session,
			Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}

		if (session.getAttribute("_orderno") == null) {
			session.setAttribute("_orderno", OrderNo.generate(partnercode));
		}

		List<Map<String, Object>> favouritelist = dishesService
				.getFavouriteList(partnerid, mobileno, buffetid);

		getOrderNum(favouritelist, (String) session.getAttribute("_orderno"),
				"id");
		model.addAttribute("favouritelist", favouritelist);
		return "/business/dishes/favouritelist";
	}

	@RequestMapping(value = "/{orderid}/{dishesid}/comment", method = RequestMethod.GET)
	public String toDishesCommentPage(@PathVariable("orderid") String orderid,
			@PathVariable("dishesid") String dishesid, HttpSession session,
			Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		if (partnercode == null) {
			throw new ControllerException("操作超时！");
		}
		Map<String, Object> dishinfo = dishesService.getDishInfo(partnercode,
				partnerid, dishesid, buffetid);
		model.addAttribute("orderid", orderid);
		model.addAttribute("dishinfo", dishinfo);
		return "/business/dishes/comment";
	}

	@ResponseBody
	@RequestMapping(value = "/comment/add", method = RequestMethod.GET)
	public Object addDishComment(@RequestParam("dishid") String dishid,
			@RequestParam("orderid") String orderid,
			@RequestParam("remark") String remark, HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String decodeRemark = null;
		try {
			decodeRemark = java.net.URLDecoder.decode(remark, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException("添加失败！", e);
		}
		return dishesService.addDishComment(dishid, orderid, decodeRemark,
				partnercode);
	}

	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public Object recommend(@RequestParam("dishid") String dishid,
			@RequestParam("orderid") String orderid, HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		return dishesService.recommend(dishid, orderid, partnercode);
	}

	@RequestMapping(value = "/buffet/{buffetid}", method = RequestMethod.GET)
	public String toBuffetPage(@PathVariable("buffetid") String buffetid,
			HttpSession session, Model model) {
		session.setAttribute("_buffetid", buffetid);
		return "/business/home";
	}

	@RequestMapping(value = "/modifybuffet/{buffetid}", method = RequestMethod.GET)
	public String toModifyBuffetPage(@PathVariable("buffetid") String buffetid,
			HttpSession session, Model model) {
		String partnerid = PartnerProfile.getPartnerID(session);
		String partnercode = PartnerProfile.getPartnerCode(session);
		List<Map<String, Object>> buffetlist = dishesService.getBuffetList(
				partnercode, partnerid);
		model.addAttribute("buffetlist", buffetlist);
		model.addAttribute("buffetid", buffetid);
		return "/business/dishes/buffetlist";
	}

	@ResponseBody
	@RequestMapping(value = "/confirmbuffet", method = RequestMethod.GET)
	public Object confirmBuffet(@RequestParam("buffetid") String buffetid,
			@RequestParam("orderno") String orderno, HttpSession session) {
		String partnerid = PartnerProfile.getPartnerID(session);
		if (buffetid.length() == 0) {
			if (session.getAttribute("_buffetid") != null) {
				session.removeAttribute("_buffetid");
			}
			buffetid = null;
		} else {
			session.setAttribute("_buffetid", buffetid);
		}
		// 更新缓存中的价格
		Map<String, Object> temporaryorder = orderService
				.refreshTemporaryOrder(orderno, buffetid, partnerid);
		return temporaryorder.get("orderdetail");
	}

	@SuppressWarnings("unchecked")
	private void getOrderNum(List<Map<String, Object>> inputlist,
			String orderno, String dishidname) {
		Map<String, Object> map = null;
		Map<String, Object> ordmap = null;
		String dishid = null;
		Map<String, Object> temporaryorder = orderService
				.getTemporaryOrder(orderno);
		List<Map<String, Object>> list = null;
		if (inputlist != null) {
			for (int i = 0; i < inputlist.size(); i++) {
				map = inputlist.get(i);
				dishid = (String) map.get(dishidname);
				if (map.containsKey("ordernum")) {
					map.remove("ordernum");
				}
				if (temporaryorder != null) {
					list = (List<Map<String, Object>>) temporaryorder
							.get("orderdetail");
					if (list != null) {
						for (int j = 0; j < list.size(); j++) {
							ordmap = list.get(j);
							if (dishid.equals(ordmap.get("dishid"))) {
								map.put("ordernum", ordmap.get("num"));
							}
						}
					}
				}
			}

		}
	}
}
