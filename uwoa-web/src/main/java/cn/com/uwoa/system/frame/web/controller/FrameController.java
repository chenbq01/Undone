package cn.com.uwoa.system.frame.web.controller;

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

import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.validate.ValidateHelper;

/**
 * 基础框架 - 控制器
 * @author liyue
 */
@Controller
public abstract class FrameController {
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	protected abstract IFrameService getService();

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	public abstract String getPage();

	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	public Map<String, Object> query(Map<String, Object> condMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		// 查询
		Map<String, Object> queryMap = getService().query(condMap);
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
	public Map<String, Object> edit(Map<String, Object> dataMap) {
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
		
		if (dataMap.get("id") != null && !dataMap.get("id").equals("")) {
			// 修改
			getService().edit(dataMap);
		}
		else {
			// 新增
			getService().add(dataMap);
		}
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
	public Map<String, Object> delete(Map<String, Object> dataMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(1);
		// 修改
		getService().delete(dataMap);
		// 标记成功
		returnMap.put("success", "true");

		// 返回对象
		return returnMap;
	}
	
	/**
	 * 查询
	 * @param sql 查询语句
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	public List<Map<String,Object>> queryBySql(String sql,PagePo pagePo){
		return getService().queryBySql(sql, pagePo);
	}
}
