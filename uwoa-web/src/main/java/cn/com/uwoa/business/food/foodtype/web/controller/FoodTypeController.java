package cn.com.uwoa.business.food.foodtype.web.controller;

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

import cn.com.uwoa.business.food.foodtype.service.IFoodTypeService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 菜品管理/菜品分类管理 - 控制器
 * @author liyue
 */
@Controller
public class FoodTypeController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(FoodTypeController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IFoodTypeService foodTypeService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)foodTypeService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/food/foodType", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/food/foodType/foodTypeList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/food/foodType/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		condMap.put("rest_id", SecurityHelper.getRestId());
		return super.query(condMap);
	}
	
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/food/foodType/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap);
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/food/foodType/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		dataMap.put("rest_id", SecurityHelper.getRestId());
		return super.delete(dataMap);
	}
}
