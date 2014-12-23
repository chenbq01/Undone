package cn.com.uwoa.system.au.user.web.controller;

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

import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.au.user.service.IUserService;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.tools.UUID;

/**
 * 系统管理/用户管理 - 控制器
 * @author liyue
 */
@Controller
public class UserController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IUserService userService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)userService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/system/au/user", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/system/au/user/userList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/au/user/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		condMap.put("rest_id", SecurityHelper.getRestId());
		return super.query(condMap);
		
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/system/au/user/queryWithSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWithSub(@RequestBody Map<String,Object> condMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		// 查询
		Map<String, Object> queryMap = getService().query(condMap);
		Map condSubMap = new HashMap();
		condSubMap.put("uid", ((Map)(((List)(queryMap.get("data"))).get(0))).get("id"));
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
	@RequestMapping(value = "/system/au/user/edit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/system/au/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		dataMap.put("rest_id", SecurityHelper.getRestId());
		return super.delete(dataMap);
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/system/au/user/password", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> password(@RequestBody Map<String,Object> dataMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(1);
		
		//调用框架查询方法
		String id = (String)dataMap.get("id");
		String password_old = (String)dataMap.get("password_old");
		String password_new = (String)dataMap.get("password_new");
		if(password_old==null || password_old.equals("") || password_new==null || password_new.equals("")){
			returnMap.put("success", "false");
		}
		else{
			String sql="UPDATE au_users SET password='"+ UUID.Md5(password_new) +"' WHERE id='"+id+"' AND password='"+UUID.Md5(password_old)+"'";
			int a = getService().executeNonQuery(sql);
			if(a>0){
				returnMap.put("success", "true");
			}
		}
		return returnMap;
	}
}
