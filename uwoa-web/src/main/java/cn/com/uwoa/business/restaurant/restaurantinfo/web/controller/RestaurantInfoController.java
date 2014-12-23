package cn.com.uwoa.business.restaurant.restaurantinfo.web.controller;

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

import cn.com.uwoa.business.restaurant.restaurantinfo.service.IRestaurantInfoService;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 餐厅设置/餐厅介绍 - 控制器
 * @author liyue
 */
@Controller
public class RestaurantInfoController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(RestaurantInfoController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IRestaurantInfoService restaurantInfoService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)restaurantInfoService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/restaurant/restaurantInfo", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/restaurant/restaurantInfo/restaurantInfoList";
	}
	

	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/restaurant/restaurantInfo/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		return super.query(condMap);
	}
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/restaurant/restaurantInfo/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap);
	}
}
