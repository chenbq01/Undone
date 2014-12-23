package cn.com.uwoa.business.mobile.web.controller;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.HashMap;
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

import cn.com.uwoa.business.mobile.service.IDishesService;
import cn.com.uwoa.business.mobile.service.IOrderService;
import cn.com.uwoa.global.cache.CacheService;
import cn.com.uwoa.global.cache.CacheService.KEY_PREFIX;
import cn.com.uwoa.global.cache.impl.CacheServiceImpl;
import cn.com.uwoa.global.exception.ControllerException;
import cn.com.uwoa.global.util.PartnerProfile;
import cn.com.uwoa.global.util.RandomCode;
import cn.com.uwoa.global.util.SMSHelper;

/**
 * Handles requests for the application home page.
 */
@Controller("mobileOrderController")
@RequestMapping("/mobileorder")
public class OrderController {

	private static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IDishesService dishesService;

	@ResponseBody
	@RequestMapping(value = "/temporary/quickbuy", method = RequestMethod.GET)
	public Object quickAddToTemporaryOrder(
			@RequestParam("orderno") String orderno,
			@RequestParam("dishid") String dishid,
			@RequestParam("dishname") String dishname,
			@RequestParam("unit") String unit,
			@RequestParam("price") String price, HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		String decodeDishname = null;
		String decodeUnit = null;
		String num = "0";
		String buffetid = null;
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}
		try {
			decodeDishname = java.net.URLDecoder.decode(dishname, "utf-8");
			decodeUnit = java.net.URLDecoder.decode(unit, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException("添加失败！", e);
		}
		Map<String, Object> dishinfo = dishesService.getDishInfo(partnercode,
				partnerid, dishid, buffetid);
		if ("0".equals(dishinfo.get("is_weigh"))) {
			num = "1";
		}
		if ("1".equals(dishinfo.get("is_weigh"))) {
			num = "0";
		}
		Map<String, Object> map = orderService.addToTemporaryOrder(orderno,
				dishid, decodeDishname, num, decodeUnit, price);
		return num;
	}

	@ResponseBody
	@RequestMapping(value = "/temporary/{orderno}/add", method = RequestMethod.GET)
	public Object addToTemporaryOrder(@PathVariable("orderno") String orderno,
			@RequestParam("dishid") String dishid,
			@RequestParam("dishname") String dishname,
			@RequestParam("num") String num, @RequestParam("unit") String unit,
			@RequestParam("price") String price) {
		String decodeDishname = null;
		String decodeUnit = null;
		try {
			decodeDishname = java.net.URLDecoder.decode(dishname, "utf-8");
			decodeUnit = java.net.URLDecoder.decode(unit, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException("添加失败！", e);
		}
		Map<String, Object> map = orderService.addToTemporaryOrder(orderno,
				dishid, decodeDishname, num, decodeUnit, price);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/temporary/{orderno}/modify", method = RequestMethod.GET)
	public Object modifyTemporaryOrder(@PathVariable("orderno") String orderno,
			@RequestParam("dishid") String dishid,
			@RequestParam("dishname") String dishname,
			@RequestParam("num") String num, @RequestParam("unit") String unit,
			@RequestParam("price") String price) {
		String decodeDishname = null;
		String decodeUnit = null;
		try {
			decodeDishname = java.net.URLDecoder.decode(dishname, "utf-8");
			decodeUnit = java.net.URLDecoder.decode(unit, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException("添加失败！", e);
		}
		Map<String, Object> map = orderService.modifyTemporaryOrder(orderno,
				dishid, decodeDishname, num, decodeUnit, price);
		return map.get("orderdetail");
	}

	@ResponseBody
	@RequestMapping(value = "/valid/org/orderno", method = RequestMethod.GET)
	public Object validOrgOrderNo(@RequestParam("orderno") String orderno,
			HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String fullorderno = orderService.validAddDishOrderNo(partnercode,
				orderno);
		if (fullorderno != null) {
			String buffetid = (String) orderService.getOrderInfo(fullorderno)
					.get("buffet_id");
			if (buffetid != null && buffetid.length() > 0) {
				session.setAttribute("_buffetid", buffetid);
			}
			session.setAttribute("_orderno", fullorderno);
			// 标识是加餐
			session.setAttribute("_isadddish", "true");
		}
		return fullorderno;
	}

	@ResponseBody
	@RequestMapping(value = "/valid/temporary/orderno", method = RequestMethod.GET)
	public Object validTemporaryOrderNo(
			@RequestParam("orderno") String orderno, HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String fullorderno = orderService.validTogetherOrderNo(partnercode,
				orderno);
		if (fullorderno != null) {
			session.setAttribute("_orderno", fullorderno);
		}
		return fullorderno;
	}

	@ResponseBody
	@RequestMapping(value = "/valid/comment/orderno", method = RequestMethod.GET)
	public Object validCommentOrderNo(@RequestParam("orderno") String orderno,
			HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String fullorderno = orderService.validCommentOrderNo(partnercode,
				orderno);
		return fullorderno;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/valid/mobilenowithrandomcode", method = RequestMethod.GET)
	public Object validMobileNoWithRandomCode(
			@RequestParam("mobileno") String mobileno,
			@RequestParam("randomno") String randomno, HttpSession session) {
		Map<String, String> mobileinfo = (Map<String, String>) session
				.getAttribute("_mobileinfo");
		if (mobileinfo != null && randomno != null && randomno != null) {
			if (mobileno.equals(mobileinfo.get("mobileno"))
					&& randomno.equals(mobileinfo.get("randomcode"))) {
				if (orderService.registerMember(mobileno)) {
					mobileinfo.put("isvalid", "true");
					return "true";
				}
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/randomcode", method = RequestMethod.GET)
	public Object getRandomCode(@RequestParam("mobileno") String mobileno,
			HttpSession session) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnername = PartnerProfile.getPartnerName(session);
		// 生成随机码
		String randomcode = RandomCode.generate();
		// 发送短信
		try {
			SMSHelper.simplesend(partnercode, mobileno, "您的动态验证码为[" + randomcode
					+ "]，退订请回复TD【" + partnername + "】。");
		} catch (RemoteException e) {
			throw new ControllerException("短信发送失败", e);
		}
		// 将手机号和随机码及验证状态保存在seesion中
		Map<String, String> mobileinfo = new HashMap<String, String>();
		mobileinfo.put("mobileno", mobileno);
		mobileinfo.put("randomcode", randomcode);
		mobileinfo.put("isvalid", "false");
		session.setAttribute("_mobileinfo", mobileinfo);
		logger.info(randomcode);
		return randomcode;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/temporary/{orderno}", method = RequestMethod.GET)
	public String toTemporaryOrderPage(@PathVariable("orderno") String orderno,
			HttpSession session, Model model) {
		String partnercode = PartnerProfile.getPartnerCode(session);
		String partnerid = PartnerProfile.getPartnerID(session);
		Map<String, Object> temporaryorder = orderService
				.getTemporaryOrder(orderno);
		List<Map<String, Object>> tables = orderService.getTables(partnercode);
		if (session.getAttribute("_mobileinfo") != null) {
			Map<String, String> mobileinfo = (Map<String, String>) session
					.getAttribute("_mobileinfo");
			if ("true".equals(mobileinfo.get("isvalid"))) {
				// 根据手机号和商户Id查找最近订单的备注和发票信息

				model.addAttribute(
						"lastorderinfo",
						orderService.getLastOrderInfo(
								mobileinfo.get("mobileno"), partnerid));

			}
		}
		List<Map<String, Object>> buffetlist = dishesService.getBuffetList(
				partnercode, partnerid);
		model.addAttribute("buffetlist", buffetlist);
		model.addAttribute("temporaryorder", temporaryorder);
		model.addAttribute("tables", tables);
		if (session.getAttribute("_isadddish") != null) {
			return "/business/order/temporaryadddishorder";
		}
		return "/business/order/temporaryorder";
	}

	@RequestMapping(value = "/disheslist/{orderno}", method = RequestMethod.GET)
	public String toDishesListPage(@PathVariable("orderno") String orderno,
			HttpSession session, Model model) {
		Map<String, Object> orderinfo = orderService.getOrderInfo(orderno);
		List<Map<String, Object>> disheslist = orderService
				.getDishesList(orderno);
		model.addAttribute("orderinfo", orderinfo);
		model.addAttribute("disheslist", disheslist);
		return "/business/order/disheslist";
	}

	@RequestMapping(value = "/mobile/prompt", method = RequestMethod.GET)
	public String toMobileValidPage(HttpSession session, Model model) {
		return "/business/order/mobilevalid";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public Object save(@RequestParam("orderno") String orderno,
			@RequestParam("position") String position,
			@RequestParam("waienum") String waienum,
			@RequestParam("numberofpeople") String numberofpeople,
			@RequestParam("remark") String remark,
			@RequestParam("isinvoice") String isinvoice,
			@RequestParam("invoicetitle") String invoicetitle,
			HttpSession session) {
		String decodeRemark = null;
		String decodeinvoicetitle = null;
		try {
			decodeRemark = java.net.URLDecoder.decode(remark, "utf-8");
			decodeinvoicetitle = java.net.URLDecoder.decode(invoicetitle,
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException("添加失败！", e);
		}
		String partnerid = PartnerProfile.getPartnerID(session);
		String votecount = PartnerProfile.getPartnerVoteCount(session);
		String buffetid = null;

		Map<String, String> mobileinfo = (Map<String, String>) session
				.getAttribute("_mobileinfo");
		String mobileno = null;
		if (mobileinfo != null) {
			if ("true".equals(mobileinfo.get("isvalid"))) {
				mobileno = (String) mobileinfo.get("mobileno");
			}
		}
		boolean isadddish = false;
		if (session.getAttribute("_isadddish") != null) {
			isadddish = true;
		}
		if (session.getAttribute("_buffetid") != null) {
			buffetid = (String) session.getAttribute("_buffetid");
		}

		Map<String, Object> temporaryorder = orderService
				.getTemporaryOrder(orderno);
		if (temporaryorder == null) {// 一起点其他人已经提交订单的情况
			// 清除Session中OrderNo
			if (session.getAttribute("_orderno") != null) {
				session.removeAttribute("_orderno");
			}
			if (session.getAttribute("_isadddish") != null) {
				session.removeAttribute("_isadddish");
			}
			if (session.getAttribute("_buffetid") != null) {
				session.removeAttribute("_buffetid");
			}
			if (session.getAttribute("_mobileinfo") != null) {
				session.removeAttribute("_mobileinfo");
			}
			return "Other";
		}

		String discountType = PartnerProfile.getPartnerDisCountType(session);
		double discountNum = PartnerProfile.getPartnerDisCountNum(session);

		boolean result = orderService.save(partnerid, votecount, orderno,
				position, waienum, numberofpeople, decodeRemark, isinvoice,
				decodeinvoicetitle, mobileno, isadddish, discountType,
				discountNum, buffetid);
		if (result) {
			// 清除Session中OrderNo
			if (session.getAttribute("_orderno") != null) {
				session.removeAttribute("_orderno");
			}
			if (session.getAttribute("_isadddish") != null) {
				session.removeAttribute("_isadddish");
			}
			if (session.getAttribute("_buffetid") != null) {
				session.removeAttribute("_buffetid");
			}
			if (session.getAttribute("_mobileinfo") != null) {
				session.removeAttribute("_mobileinfo");
			}
			// 清除Cache中的临时订单
			CacheService cache = CacheServiceImpl.getInstance();
			cache.remove(KEY_PREFIX.TEMPORARY_ORDER + "_" + orderno);
		}
		return String.valueOf(result);
	}
}
