package cn.com.uwoa.system.dictionary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.uwoa.system.dictionary.web.controller.DictionaryController;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 数据字典帮助类 - 帮助类
 * @author liyue
 */
public class DictionaryHelper {
	/**
	 * 字典表Map
	 */
	public static Map<String,List<Map<String,String>>> dictionaryMap = new HashMap<String,List<Map<String,String>>>();
	
	/**
	 * 获得下拉选项HTML
	 * @param key 字典关键字
	 * @return 选项HTML字符串
	 */
	public static String optionHtml(String key,String type){
		if(dictionaryMap.get(key)==null){
			//获得数据字典控制器
			DictionaryController c=SpringContextHolder.getBean("dictionaryController");
			Map<String,Object> condMap = new HashMap<String,Object>();
			condMap.put("key", key);
			condMap.put("page", "-1");
			List<Map<String, Map>> queryData = (List)(c.queryWithSub(condMap).get("data"));
			if(queryData.size()>0){
				dictionaryMap.put(key,(List<Map<String, String>>)((Map)queryData.get(0).get("sub")).get("data"));
			}
		}
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
		for(int i=0;i<dictionaryMap.get(key).size();i++){
			Map<String,String> row = dictionaryMap.get(key).get(i);
			returnString.append("<option value=\""+row.get("value")+"\">"+row.get("name")+"</option>");
		}
		return returnString.toString();
	}
}
