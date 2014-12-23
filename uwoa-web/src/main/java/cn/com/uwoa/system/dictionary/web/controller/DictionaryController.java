package cn.com.uwoa.system.dictionary.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.system.dictionary.service.IDictionaryService;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 系统管理/数据字典 - 控制器
 * @author liyue
 */
@Controller
public class DictionaryController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IDictionaryService dictionaryService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)dictionaryService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/system/dictionary", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/system/dictionary/dictionaryList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/dictionary/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		return super.query(condMap);
		
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/dictionary/queryWithSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWithSub(@RequestBody Map<String,Object> condMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		// 查询
		Map<String, Object> queryMap = getService().query(condMap);
		Map condSubMap = new HashMap();
		condSubMap.put("dictionary_id", ((Map)(((List)(queryMap.get("data"))).get(0))).get("id"));
		condSubMap.put("page", "-1");
		Map<String, Object> querySubMap = getService().querySub(condSubMap);
		((Map)(((List)(queryMap.get("data"))).get(0))).put("sub", querySubMap);
		
		// 标记成功
		returnMap.put("success", "true");
		// 记录数据
		returnMap.put("data", queryMap.get("data"));
		// 记录页信息
		returnMap.put("pageInfo", queryMap.get("pageInfo"));
		returnMap.put("pageHtml", queryMap.get("pageHtml"));

		// 返回对象
		return returnMap;
		
	}
	
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/system/dictionary/edit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/system/dictionary/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap);
	}
}
