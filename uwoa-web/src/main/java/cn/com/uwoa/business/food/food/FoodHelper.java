package cn.com.uwoa.business.food.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.com.uwoa.business.food.food.web.controller.FoodController;
import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 菜品管理/菜品维护管理 - 帮助类
 * @author liyue
 */
public class FoodHelper {
	/**
	 * 获得选项HTML
	 * @return 选项HTML字符串
	 */
	public static String optionSubHtml(String type){
		//获得数据字典控制器
		FoodController c=SpringContextHolder.getBean("foodController");
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
			String a[]=SpecialOfferHelper.getFoodPriceInfo(row.get("id"),"",SecurityHelper.getRestId(),0.8);
			returnString.append("<option value=\""+row.get("id")+"\" rowid=\"'+$('#maxRow').val()+'\" unit=\""+row.get("unit_name")+"\" price=\""+a[0]+"\" favor_price=\""+a[1]+"\" is_favor=\""+a[2]+"\" is_discount=\""+a[3]+"\" buffet=\""+a[5]+"\">["+row.get("food_code")+"]"+row.get("food_name")+"("+row.get("mnem_code")+")</option>");
		}
		return returnString.toString();
	}
	
	/**
	 * 列表单选HTML
	 */
	public static String checkboxListHtml(){
		//获得数据字典控制器
		FoodController c=SpringContextHolder.getBean("foodController");
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("page", "-1");
		List<Map<String, String>> queryData = (List)(c.query(condMap).get("data"));

		StringBuffer returnString = new StringBuffer();

		Map foodType = new TreeMap();
		for(int i=0;i<queryData.size();i++){
			Map<String,String> row = queryData.get(i);
			String key=row.get("type_id");
			if(foodType.get(key)==null){
				foodType.put(key, new ArrayList());
			}
			((List)foodType.get(key)).add(row);
		}
		
		Iterator it = foodType.values().iterator();
		while(it.hasNext()){
			List lst = (List)it.next();
			returnString.append("<tr class=\"ui-widget-header \"><td colspan=\"6\">"+(((Map)lst.get(0)).get("type_name"))+"</td></tr>");
			String listRowHtml = "";
			for(int i=1;i<=lst.size();i++){
				String id=(String)((Map)lst.get(i-1)).get("id");
				listRowHtml+="<td width=\"3%\"><input type=\"checkbox\"  name=\"food_id_"+id+"\" style=\"width:20px\" value=\""+id+"\"/></td><td width=\"30%\">"+((Map)lst.get(i-1)).get("food_name")+"</td>";
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
			
		}
		return returnString.toString();
	}
}
