package cn.com.uwoa.report.web.controller;

import java.rmi.RemoteException;
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

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.report.service.IReportService;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.validate.ValidateHelper;

/**
 * 报表/报表 - 控制器
 * @author liyue
 */
@Controller
public class ReportController4 extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReportController4.class);

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
	@RequestMapping(value = "/report/report4", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/report/reportList4";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/report/report4/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		String sql="select member.mobile_num,ord_order_item.food_id,count(ord_order_item.id) countdc,count(food_recommend.id) counttj from  ord_order ";
		sql+="left join member on member.id=ord_order.member_id ";
		sql+="left join ord_order_item on ord_order_item.order_id=ord_order.id ";
		sql+="left join food_recommend on food_recommend.order_id=ord_order.id and food_recommend.food_id=ord_order_item.food_id ";
		sql+="where member_id is not NULL ";

		if(condMap.get("beginDate")!=null && !condMap.get("beginDate").equals("")){
			sql+=" AND TO_DAYS(ord_order.create_time)>=TO_DAYS('"+condMap.get("beginDate")+"')";
		}
		if(condMap.get("endDate")!=null && !condMap.get("endDate").equals("")){
			sql+=" AND TO_DAYS(ord_order.create_time)<=TO_DAYS('"+condMap.get("endDate")+"')";
		}
		if(condMap.get("food_id")!=null && !condMap.get("food_id").equals("")){
			sql+=" AND ord_order_item.food_id='"+condMap.get("food_id")+"'";
		}
		sql+=" group by member.mobile_num,ord_order_item.food_id ";
		
		//返回结果集
		Map<String,Object> returnMap = new HashMap<String,Object>(2);
		//获得页号
		int page=condMap.get("page")!=null?Integer.parseInt(condMap.get("page").toString()):1;
		//计算页信息
		PagePo pageInfo=null;
		if(page==-1){
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml","");
		}
		else{
			pageInfo=new PagePo(page,Integer.parseInt(getService().queryBySql("SELECT count(1) rowcount FROM ("+sql+") a", null).get(0).get("rowcount").toString()));
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml",pageInfo.toHtml());
		}
		returnMap.put("data", getService().queryBySql(sql, pageInfo));
		returnMap.put("success","true");
		return returnMap;
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/report/report4/sendMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMsg(@RequestBody Map<String,Object> dataMap) {
		// 声明返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>(1);
		// 发送
		String[] idArray = ((String)dataMap.get("ids")).split(",");
		for(int i=0;i<idArray.length;i++){
			try {
				cn.com.uwoa.global.util.SMSHelper.adsimplesend(SecurityHelper.getRestId(),idArray[i],(String)dataMap.get("msg"));
			} catch (RemoteException e) {
				returnMap.put("success", "false");
				return returnMap;
			}
		}
		// 标记成功
		returnMap.put("success", "true");

		// 返回对象
		return returnMap;
	}
}
