package cn.com.uwoa.business.system.restaurant.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.business.system.restaurant.service.IRestaurantService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 平台管理/餐厅管理 - 控制器
 * @author liyue
 */
@Controller
public class RestaurantController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IRestaurantService restaurantService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)restaurantService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/system/restaurant", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/system/restaurant/restaurantList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/restaurant/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		if(SecurityHelper.getIsGroup()){
			condMap.put("parent_id", SecurityHelper.getRestId());
		}
		return super.query(condMap);
	}
	
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/system/restaurant/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		dataMap.put("is_group", "1");
		if(SecurityHelper.getIsGroup()){
			dataMap.put("parent_id", SecurityHelper.getRestId());
		}
		//调用框架查询方法
		return super.edit(dataMap);
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/system/restaurant/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		if(SecurityHelper.getIsGroup()){
			dataMap.put("parent_id", SecurityHelper.getRestId());
		}
		return super.delete(dataMap);
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/restaurant/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> repeat(@RequestBody Map<String,Object> condMap) {
		String id=(String)condMap.get("id");
		String inputName = (String)condMap.get("inputName");
		condMap.remove("id");
		condMap.remove("inputName");
		Map<String, Object> temp= super.query(condMap);
		String errorInfo="";
		boolean same = false;
		for(int i=0;i<((List)temp.get("data")).size();i++){
			Map row = (Map)((List)temp.get("data")).get(i);
			if(row.get("id").equals(id)){
				
			}
			else{
				same=true;
			}
		}
		if(same){
			errorInfo=inputName+"重复";
		}
		Map<String, Object> returnMap = new HashMap<String, Object>(2);
		returnMap.put("errorInfo", errorInfo);
		if(errorInfo.equals("")){
			// 标记成功
			returnMap.put("success", "true");
		}
		else{
			// 标记错误
			returnMap.put("success", "error");
		}
		return returnMap;
	}
}
