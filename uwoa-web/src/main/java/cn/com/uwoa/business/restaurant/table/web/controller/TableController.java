package cn.com.uwoa.business.restaurant.table.web.controller;

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

import cn.com.uwoa.business.restaurant.table.service.ITableService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.validate.ValidateHelper;

/**
 * 餐厅设置/餐桌管理 - 控制器
 * @author liyue
 */
@Controller
public class TableController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(TableController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private ITableService tableService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)tableService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/restaurant/table", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/restaurant/table/tableList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/restaurant/table/query", method = RequestMethod.POST)
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
	@RequestMapping(value = "/restaurant/table/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		if(dataMap.get("order_num")==null || dataMap.get("order_num").equals("")){
			dataMap.put("order_num","0");
		}
		//调用框架查询方法
		return super.edit(dataMap);
	}
	
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/restaurant/table/addBatch", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBatch(@RequestBody Map<String,Object> dataMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(2);
				
		//校验
		if(dataMap.get("validate")!=null){
			String errorInfo = ValidateHelper.checkValidate((Map)dataMap.get("validate"));
			returnMap.put("errorInfo", errorInfo);
			if(!errorInfo.equals("")){
				// 标记错误
				returnMap.put("success", "error");
				// 返回对象
				return returnMap;
			}
			dataMap.remove("validate");
		}
		
		((ITableService)getService()).addBatch(dataMap);

		// 标记成功
		returnMap.put("success", "true");

		// 返回对象
		return returnMap;
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/restaurant/table/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		dataMap.put("rest_id", SecurityHelper.getRestId());
		return super.delete(dataMap);
	}
}
