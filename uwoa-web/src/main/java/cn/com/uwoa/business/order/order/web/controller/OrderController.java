package cn.com.uwoa.business.order.order.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.business.food.specialoffer.web.controller.SpecialOfferController;
import cn.com.uwoa.business.order.order.service.IOrderService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.global.util.OrderNo;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.tools.SpringContextHolder;
import cn.com.uwoa.system.validate.ValidateHelper;

/**
 * 订单管理/订单列表 - 控制器
 * @author liyue
 */
@Controller
public class OrderController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IOrderService orderService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)orderService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/order/order", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/order/order/orderList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/order/order/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		condMap.put("rest_id", SecurityHelper.getRestId());
		//调用框架查询方法
		return super.query(condMap);
		
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/order/order/queryWithSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryWithSub(@RequestBody Map<String,Object> condMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		
		// 查询
		condMap.put("rest_id", SecurityHelper.getRestId());
		Map<String, Object> queryMap = getService().query(condMap);
		try {
			AssemblyHelper.assembly(queryMap.get("data"), "rest_restaurant", "rest_id", "open_vip", "id", "open_vip", null);
			AssemblyHelper.assembly(queryMap.get("data"), "rest_restaurant", "rest_id", "discout_type", "id", "discout_type", null);
			AssemblyHelper.assembly(queryMap.get("data"), "rest_restaurant", "rest_id", "discount_num", "id", "discount_num", null);
			AssemblyHelper.assembly(queryMap.get("data"), "food_comment", "id", "food_comment_id", "order_id", "id", null);
			AssemblyHelper.assembly(queryMap.get("data"), "food_recommend", "id", "food_recommend_id", "order_id", "id", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map condSubMap = new HashMap();
		condSubMap.put("order_id", ((Map)(((List)(queryMap.get("data"))).get(0))).get("id"));
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
	 * 查询桌子
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/order/order/queryTable", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTable(Map<String,Object> condMap){
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		
		//获得数据字典控制器
		SpecialOfferController c=SpringContextHolder.getBean("specialOfferController");
		String sql = "SELECT rest_table.id, rest_table.table_name, ord_order.waie_num, ord_order.order_no, ord_order.status, SUM(ord_order.order_amount) order_amount, SUM(ord_order.favor_amount) favor_amount FROM rest_table LEFT JOIN ord_order ON ( ord_order.table_id = rest_table.id AND ord_order.delete_flag = '0' AND ord_order. STATUS IN ('01','02') ) WHERE rest_table.delete_flag = '0' AND (rest_table.rest_id='"+SecurityHelper.getRestId()+"' OR rest_table.rest_id IS NULL OR rest_table.rest_id = '') GROUP BY table_id, table_name, order_no ORDER BY rest_table.order_num,ord_order.waie_num";
		// 标记成功
		returnMap.put("success", "true");
		// 记录数据
		returnMap.put("data", c.queryBySql(sql, null));
		
		return returnMap;
	}
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/order/order/edit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/order/order/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		dataMap.put("rest_id", SecurityHelper.getRestId());
		//调用框架查询方法
		return super.delete(dataMap);
	}
	
	/**
	 * 打印
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/order/order/print/{orderId}", method = RequestMethod.GET)
	public String print(@PathVariable("orderId") String orderId, Model model) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		// 查询
		Map condMap = new HashMap();
		condMap.put("id", orderId);
		condMap.put("rest_id", SecurityHelper.getRestId());
		condMap.put("page", "-1");
		Map<String, Object> queryMap = getService().query(condMap);
		if(queryMap.get("data")!=null && ((List)queryMap.get("data")).size()>0){
			Map condSubMap = new HashMap();
			condSubMap.put("order_id", orderId);
			condSubMap.put("page", "-1");
			Map<String, Object> querySubMap = getService().querySub(condSubMap);
			((Map)(((List)(queryMap.get("data"))).get(0))).put("sub", querySubMap);
			String sql="SELECT * FROM ord_batch WHERE order_id='"+orderId+"' AND order_batch<>'0' ORDER BY order_batch";
			List queryBatchList = getService().queryBySql(sql, null);
			((Map)(((List)(queryMap.get("data"))).get(0))).put("batch", queryBatchList);
			model.addAttribute("orderInfo",queryMap);
		}
		else{
			model.addAttribute("orderInfo",null);
		}
		
		
		return "/order/order/orderPrint";
	}
	
	/**
	 * 打印批次
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/order/order/printBatch/{batchId}", method = RequestMethod.GET)
	public String printBatch(@PathVariable("batchId") String batchId, Model model) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		
		String sql = "SELECT ord_order.*,ord_batch.order_batch,ord_batch.memo FROM ord_order INNER JOIN ord_batch ON ord_order.id = ord_batch.order_id WHERE ord_order.rest_id='"+SecurityHelper.getRestId()+"' AND ord_batch.id='"+batchId+"'";
		List<Map<String, Object>> queryList=getService().queryBySql(sql,null);
		Map<String, Object> queryMap = new HashMap();
		if(queryList.size()>0){
			try {
				AssemblyHelper.assembly(queryList, "rest_table", "table_id", "table_name", "id", "table_name", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queryMap.put("data", queryList);
		}
		if(queryMap.get("data")!=null && ((List)queryMap.get("data")).size()>0){
			Map condSubMap = new HashMap();
			condSubMap.put("batch_id", batchId);
			condSubMap.put("page", "-1");
			Map<String, Object> querySubMap = getService().querySub(condSubMap);
			((Map)(((List)(queryMap.get("data"))).get(0))).put("sub", querySubMap);
			model.addAttribute("orderInfo",queryMap);
		}
		else{
			model.addAttribute("orderInfo",null);
		}
		
		
		return "/order/order/orderPrintBatch";
	}
	
	/**
	 * 结账
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/order/order/checkout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkout(@RequestBody Map<String,Object> dataMap) {
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
		
		// 修改
		((IOrderService)getService()).checkout(dataMap);
		// 标记成功
		returnMap.put("success", "true");

		// 返回对象
		return returnMap;
	}
}
