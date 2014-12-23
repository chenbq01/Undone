package cn.com.uwoa.system.validate.web.controller;

import java.util.HashMap;
import java.util.Iterator;
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
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.tools.SpringContextHolder;
import cn.com.uwoa.system.validate.ValidateHelper;

/**
 * 数据字典 - 控制器
 * @author liyue
 */
@Controller
public class ValidateController {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/validate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> validate) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		
		//校验
		String errorInfo = ValidateHelper.checkValidate(validate);
		
		returnMap.put("errorInfo", errorInfo);
		if(errorInfo.equals("")){
			// 标记成功
			returnMap.put("success", "true");
		}
		else{
			// 标记错误
			returnMap.put("success", "error");
		}
		
		// 返回对象
		return returnMap;
	}
}
