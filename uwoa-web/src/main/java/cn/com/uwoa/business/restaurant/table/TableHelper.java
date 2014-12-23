package cn.com.uwoa.business.restaurant.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.uwoa.business.food.foodtype.web.controller.FoodTypeController;
import cn.com.uwoa.business.restaurant.table.web.controller.TableController;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 餐厅设置/餐桌管理 - 帮助类
 * @author liyue
 */
public class TableHelper {
	/**
	 * 获得选项HTML
	 * @return 选项HTML字符串
	 */
	public static String optionHtml(String type){
		//获得数据字典控制器
		TableController c=SpringContextHolder.getBean("tableController");
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("page", "-1");
		List<Map<String, String>> queryData = (List)(c.query(condMap).get("data"));

		StringBuffer returnString = new StringBuffer();
		if(type.equals("SELECT")){
			returnString.append("<option value=\"\">请选择</option>");
		}
		if(type.equals("NULL")){
			returnString.append("<option value=\"\"></option>");
		}
		else if(type.equals("ALL")){
			returnString.append("<option value=\"\">全部</option>");
		}
		for(int i=0;i<queryData.size();i++){
			Map<String,String> row = queryData.get(i);
			returnString.append("<option value=\""+row.get("id")+"\">"+row.get("table_name")+"</option>");
		}
		return returnString.toString();
	}
}
