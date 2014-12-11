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

import cn.sofamovie.admin.domain.UserInfo;
import cn.sofamovie.admin.service.UserInfoService;
import cn.sofamovie.admin.util.StringHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("--退出登录--");
		}
		session.removeAttribute("_USER_INFO");
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问登录页--");
		}
		return "/user/login";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "_json=true")
	public Object login(String username, String password, HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("--请求登录--");
		}
		Map<String, String> map = new HashMap<String, String>();
		List<UserInfo> list = userInfoService.findUserByUsernameAndPassword(
				username, password);
		if (list != null && list.size() == 1) {
			session.setAttribute("_USER_INFO", list.get(0));
			map.put("flag", "true");
		} else {
			map.put("flag", "false");
			map.put("msg", "用户名或密码错误！");
		}
		return map;
	}

	@RequestMapping(value = "/modifypassword", method = RequestMethod.GET)
	public String toModifyPassword(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问修改密码页--");
		}
		model.addAttribute("_ACTIVED_MENU", "4");
		model.addAttribute("_MENU_TITLE", "修改密码");
		return "/user/modifypassword";
	}

	@ResponseBody
	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST, params = "_json=true")
	public Object modifyPassword(String password, String newpassword,
			String confirmpassword, HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("--修改密码--");
		}
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = (UserInfo) session.getAttribute("_USER_INFO");
		if (!userInfo.getPassword().equals(
				StringHelper.encryptStringByMD5(password))) {
			map.put("flag", "false");
			map.put("msg", "当前密码输入有误！");
			return map;
		}
		if (newpassword == null || confirmpassword == null
				|| !newpassword.equals(confirmpassword)) {
			map.put("flag", "false");
			map.put("msg", "新的密码输入有误或新的密码与确认密码不一致！");
			return map;
		}
		boolean flag = userInfoService.modifyPasswordByUserID(newpassword,
				userInfo.getId().toString());
		if (!flag) {
			map.put("flag", "false");
			map.put("msg", "系统错误，修改密码失败！");
			return map;
		}
		map.put("flag", "true");
		return map;
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问管理其它账户页--");
		}
		model.addAttribute("_ACTIVED_MENU", "4");
		model.addAttribute("_MENU_TITLE", "管理其它账户");
		return "/user/manage";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查看所有用户--");
		}
		List<UserInfo> list = userInfoService.findNonAdministratorUser();
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST, params = "_json=true")
	public Object enable(String userid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--启用账号--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = userInfoService.enableUserAccount(userid);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST, params = "_json=true")
	public Object disable(String userid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--停用账号--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = userInfoService.disableUserAccount(userid);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, params = "_json=true")
	public Object resetPassword(String userid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--重置密码--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = userInfoService.resetPassword(userid);
		map.put("flag", String.valueOf(flag));
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "_json=true")
	public Object addUser(String username) {
		if (logger.isDebugEnabled()) {
			logger.debug("--添加用户--");
		}
		String flag = null;
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = userInfoService.createUser(username, "123456", 0);
		if (userInfo != null && userInfo.getId() != null) {
			flag = "true";
		} else {
			flag = "false";
		}
		map.put("flag", flag);
		return map;
	}

}
