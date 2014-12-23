package cn.com.uwoa.report.web.controller;

import java.util.List;
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

import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.report.service.IReportService;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;

/**
 * 报表/报表 - 控制器
 * @author liyue
 */
@Controller
public class ReportController2 extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReportController2.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IReportService reportService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)reportService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/report/report2", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/report/reportList2";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/report/report2/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		String selectRECO="SELECT a.food_id,a.countreco,@rownum:=@rownum+1 orderbyreco FROM (SELECT @rownum:=0,food_recommend.food_id,COUNT(food_recommend.id) countreco FROM food_recommend WHERE rest_id='"+SecurityHelper.getRestId()+"'";
		if(condMap.get("beginDate")!=null && !condMap.get("beginDate").equals("")){
			selectRECO+=" AND TO_DAYS(food_recommend.reco_time)>=TO_DAYS('"+condMap.get("beginDate")+"')";
		}
		if(condMap.get("endDate")!=null && !condMap.get("endDate").equals("")){
			selectRECO+=" AND TO_DAYS(food_recommend.reco_time)<=TO_DAYS('"+condMap.get("endDate")+"')";
		}
		selectRECO+=" group by food_recommend.food_id order by countreco DESC) a";
		
		String sql="SELECT food_food.food_name, food_foodtype.type_name, count(ord_order_item.food_count) food_count,b.countreco,b.orderbyreco FROM food_food LEFT JOIN food_foodtype ON food_foodtype.id = food_food.type_id LEFT JOIN ord_order_item ON ord_order_item.food_id = food_food.id LEFT JOIN ord_order ON ord_order_item.order_id = ord_order.id LEFT JOIN ("+selectRECO+") b ON b.food_id=food_food.id WHERE food_food.delete_flag='0' AND ord_order.delete_flag='0' AND ord_order.status='03' AND food_food.rest_id='"+SecurityHelper.getRestId()+"'";
		if(condMap.get("beginDate")!=null && !condMap.get("beginDate").equals("")){
			sql+=" AND TO_DAYS(ord_order.create_time)>=TO_DAYS('"+condMap.get("beginDate")+"')";
		}
		if(condMap.get("endDate")!=null && !condMap.get("endDate").equals("")){
			sql+=" AND TO_DAYS(ord_order.create_time)<=TO_DAYS('"+condMap.get("endDate")+"')";
		}
		if(condMap.get("type_id")!=null && !condMap.get("type_id").equals("")){
			sql+=" AND food_foodtype.id='"+condMap.get("type_id")+"'";
		}
		sql+=" GROUP BY food_name, type_name ORDER BY food_count DESC";
		
		//返回结果集
		Map<String,Object> returnMap = new HashMap<String,Object>(2);
		//获得页号
		int page=condMap.get("page")!=null?Integer.parseInt(condMap.get("page").toString()):1;
		//计算页信息
		PagePo pageInfo=null;
		page=-1;
		if(page==-1){
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml","");
		}
		else{
			pageInfo=new PagePo(page,Integer.parseInt(getService().queryBySql("SELECT count(1) rowcount FROM ("+sql+") a", null).get(0).get("rowcount").toString()));
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml",pageInfo.toHtml());
		}
		List data=(List)getService().queryBySql(sql, pageInfo);
		while(data.size()>20){
			data.remove(10);
		}
		returnMap.put("data", data);
		returnMap.put("success","true");
		return returnMap;
	}
}
