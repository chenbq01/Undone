package cn.com.uwoa.business.restaurant.memberdiscount.web.controller;

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

import cn.com.uwoa.business.restaurant.memberdiscount.service.IMemberDiscountService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 餐厅设置/会员优惠菜品分类 - 控制器
 * @author liyue
 */
@Controller
public class MemberDiscountController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(MemberDiscountController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IMemberDiscountService memberDiscountService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)memberDiscountService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/restaurant/memberDiscount", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/restaurant/memberDiscount/memberDiscountList";
	}
	

	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/restaurant/memberDiscount/query", method = RequestMethod.POST)
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
	@RequestMapping(value = "/restaurant/memberDiscount/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		Map sub = (Map)dataMap.get("sub");
		dataMap.put("sub",null);
		if(sub!=null){
			Map idMap = new HashMap();
			String ids=(String)sub.get("ids");
			if(ids!=null && ids.length()>0){
				String idArray[] = ids.split(",");
				for(int i=0;i<idArray.length;i++){
					Map row = new HashMap();
					row.put("id", idArray[i]);
					idMap.put(i,row);
				}
			}
			dataMap.put("sub", idMap);
		}
		//调用框架查询方法
		return super.edit(dataMap);
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/restaurant/memberDiscount/queryWithSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWithSub(@RequestBody Map<String,Object> condMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		// 查询
		List queryList=getService().queryBySql("SELECT * FROM food_foodtype WHERE 1=1  AND `rest_id` IN ('"+SecurityHelper.getRestId()+"') AND delete_flag='0'", null);
		
		// 标记成功
		returnMap.put("success", "true");
		// 记录数据
		returnMap.put("data", queryList);

		// 返回对象
		return returnMap;
		
	}
}
