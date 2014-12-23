package cn.com.uwoa.report.web.controller;

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
public class ReportController1 extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReportController1.class);

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
	@RequestMapping(value = "/report/report1", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/report/reportList1";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/report/report1/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		String sql="SELECT *,(pay_amount-(CASE WHEN pay_change>0 THEN pay_change ELSE 0 END)) spay_amount FROM ord_order WHERE delete_flag='0' AND status='03' AND rest_id='"+SecurityHelper.getRestId()+"'";
		if(condMap.get("beginDate")!=null && !condMap.get("beginDate").equals("")){
			sql+=" AND TO_DAYS(create_time)>=TO_DAYS('"+condMap.get("beginDate")+"')";
		}
		if(condMap.get("endDate")!=null && !condMap.get("endDate").equals("")){
			sql+=" AND TO_DAYS(create_time)<=TO_DAYS('"+condMap.get("endDate")+"')";
		}
		if(condMap.get("is_huiyuan")!=null && !condMap.get("is_huiyuan").equals("")){
			if(condMap.get("is_huiyuan").equals("1")){
				sql+=" AND member_id IS not NULL";
			}
			else{
				sql+=" AND member_id IS NULL";
			}
			
		}
		
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
		
		//查合计
		sql="SELECT COUNT(id) countOrder,SUM(order_amount) order_amount,SUM(favor_amount) favor_amount,SUM(pay_amount-(CASE WHEN pay_change>0 THEN pay_change ELSE 0 END)) spay_amount,SUM(person_count) person_count FROM ("+sql+") a";
		returnMap.put("dataHJ", getService().queryBySql(sql, null));
		returnMap.put("success","true");
		return returnMap;
	}
}
