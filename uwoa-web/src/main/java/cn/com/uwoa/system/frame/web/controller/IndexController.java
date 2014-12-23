package cn.com.uwoa.system.frame.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.system.frame.service.IFrameService;

/**
 * 主页 - 控制器
 * @author liyue
 */
@Controller
public class IndexController {
	/**
	 * 获取框架页
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage() {
		return "/system/frame/index";
	}
	
	/**
	 * 获取上传
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
	public String getUploadPage() {
		return "/base/upload";
	}
}
