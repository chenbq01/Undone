package cn.com.uwoa.business.authorize.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import cn.com.uwoa.business.authorize.service.IAuthorizeService;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

@Controller
public class AuthorizeController  extends FrameController {

	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IAuthorizeService authorizeService;
	
	@Override
	protected IFrameService getService() {
		return (IFrameService)authorizeService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/user/userlist", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/system/user/userList";
	}
	
	public int addRestAdmin(String userName,String password,String rest_id){
		return authorizeService.addRestAdminUser(userName, password, rest_id);
	}
	
}
