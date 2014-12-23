package cn.com.uwoa.business.food.foodtype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.com.uwoa.business.food.food.web.controller.FoodController;
import cn.com.uwoa.business.food.foodtype.web.controller.FoodTypeController;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 菜品管理/菜品分类管理 - 帮助类
 * @author liyue
 */
public class FoodTypeHelper {
	/**
	 * 获得选项HTML
	 * @return 选项HTML字符串
	 */
	public static String optionHtml(String type){
		//获得数据字典控制器
		FoodTypeController c=SpringContextHolder.getBean("foodTypeController");
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
			returnString.append("<option value=\""+row.get("id")+"\">"+row.get("type_name")+"</option>");
		}
		return returnString.toString();
	}
	
	/**
	 * 列表单选HTML
	 */
	public static String checkboxListHtml(){
		//获得数据字典控制器
		FoodTypeController c=SpringContextHolder.getBean("foodTypeController");
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("page", "-1");
		List<Map<String, String>> lst = (List)(c.query(condMap).get("data"));

		StringBuffer returnString = new StringBuffer();

		String listRowHtml = "";
		for(int i=1;i<=lst.size();i++){
			String id=(String)((Map)lst.get(i-1)).get("id");
			listRowHtml+="<td width=\"3%\"><input type=\"checkbox\"  name=\"foodtype_id_"+id+"\" style=\"width:20px\" value=\""+id+"\"/></td><td width=\"30%\">"+((Map)lst.get(i-1)).get("type_name")+"</td>";
			if(i%3==0){
				returnString.append("<tr>"+listRowHtml+"</tr>");
				listRowHtml="";
			}
		}
		for(int i=0;i<(3-lst.size()%3);i++){
			listRowHtml+="<td width=\"3%\"></td><td width=\"30%\"></td>";
		}
		if((3-lst.size()%3)!=0){
			returnString.append("<tr>"+listRowHtml+"</tr>");
		}

		return returnString.toString();
	}
}
