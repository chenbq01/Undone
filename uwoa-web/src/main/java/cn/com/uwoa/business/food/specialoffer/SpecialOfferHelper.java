package cn.com.uwoa.business.food.specialoffer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.com.uwoa.business.food.food.web.controller.FoodController;
import cn.com.uwoa.business.food.specialoffer.web.controller.SpecialOfferController;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 菜品管理/限时特价 - 帮助类
 * 
 * @author liyue
 */
public class SpecialOfferHelper {
	public static Map foodPriceMap = new HashMap();

	/**
	 * 获得选项HTML
	 * 
	 * @return 选项HTML字符串
	 */
	public static String getFoodPrice(String id) {
		List<Map<String, Object>> foodList = (List) foodPriceMap
				.get(SecurityHelper.getRestId() + "_" + id);
		if (foodList == null || foodList.size() == 0) {
			// 获得数据字典控制器
			SpecialOfferController c = SpringContextHolder
					.getBean("specialOfferController");
			String sql = "SELECT food_food.id food_id, food_food.rest_id, food_specialoffer.begin_date, food_specialoffer.end_date, food_specialoffer.begin_time, food_specialoffer.end_time, food_specialoffer.favor_week, food_specialoffer.favor_day, food_food.price, food_specialoffer_item.favor_price FROM food_food LEFT JOIN food_specialoffer_item ON food_food.id = food_specialoffer_item.food_id LEFT JOIN food_specialoffer ON food_specialoffer.id = food_specialoffer_item.spec_id WHERE 1 = 1 AND food_food.delete_flag='0' AND (food_specialoffer.delete_flag='0' OR food_specialoffer.delete_flag is null) AND (food_specialoffer_item.delete_flag='0' OR food_specialoffer_item.delete_flag is null) AND food_food.id = '"
					+ id + "' ORDER BY food_specialoffer_item.favor_price";
			foodList = c.queryBySql(sql, null);
			foodPriceMap.put(SecurityHelper.getRestId() + "_" + id, foodList);
		}

		String price = "0";
		for (int i = 0; i < foodList.size(); i++) {
			Map<String, Object> row = foodList.get(i);
			Date date = new Date();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String day = new SimpleDateFormat("d").format(date);
			String now = new SimpleDateFormat("HH:mm:00").format(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(Calendar.DAY_OF_WEEK);

			if (i == 0) {
				price = row.get("price").toString();
			}

			if (today.compareTo(row.get("begin_date").toString()) >= 0
					&& today.compareTo(row.get("end_date").toString()) <= 0
					&& now.compareTo(row.get("begin_time").toString()) >= 0
					&& now.compareTo(row.get("end_time").toString()) <= 0
					&& row.get("favor_week")
							.toString()
							.indexOf(
									"\"favor_week_" + week + "\":\"favor_week_"
											+ week + "\"") > 0
					&& row.get("favor_day")
							.toString()
							.indexOf(
									"\"favor_day_" + day + "\":\"favor_day_"
											+ day + "\"") > 0) {
				if (row.get("favor_price") != null) {
					return row.get("favor_price").toString();
				}
			}
		}

		return price;
	}

	public static String getFoodPrice(String id, String rest_id) {
		List<Map<String, Object>> foodList = (List) foodPriceMap.get(rest_id
				+ "_" + id);
		if (foodList == null || foodList.size() == 0) {
			// 获得数据字典控制器
			SpecialOfferController c = SpringContextHolder
					.getBean("specialOfferController");
			String sql = "SELECT food_food.id food_id, food_food.rest_id, food_specialoffer.begin_date, food_specialoffer.end_date, food_specialoffer.begin_time, food_specialoffer.end_time, food_specialoffer.favor_week, food_specialoffer.favor_day, food_food.price, food_specialoffer_item.favor_price FROM food_food LEFT JOIN food_specialoffer_item ON food_food.id = food_specialoffer_item.food_id  AND food_specialoffer_item.delete_flag='0' LEFT JOIN food_specialoffer ON food_specialoffer.id = food_specialoffer_item.spec_id AND food_specialoffer.delete_flag='0' WHERE 1 = 1 AND food_food.delete_flag='0'  AND food_food.id = '"
					+ id + "' ORDER BY food_specialoffer_item.favor_price";
			foodList = c.queryBySql(sql, null);
			foodPriceMap.put(rest_id + "_" + id, foodList);
		}

		String price = "0";
		for (int i = 0; i < foodList.size(); i++) {
			Map<String, Object> row = foodList.get(i);
			Date date = new Date();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String day = new SimpleDateFormat("d").format(date);
			String now = new SimpleDateFormat("HH:mm:00").format(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(Calendar.DAY_OF_WEEK);

			if (i == 0) {
				price = row.get("price").toString();
			}

			if (today.compareTo(row.get("begin_date").toString()) >= 0
					&& today.compareTo(row.get("end_date").toString()) <= 0
					&& now.compareTo(row.get("begin_time").toString()) >= 0
					&& now.compareTo(row.get("end_time").toString()) <= 0
					&& row.get("favor_week")
							.toString()
							.indexOf(
									"\"favor_week_" + week + "\":\"favor_week_"
											+ week + "\"") > 0
					&& row.get("favor_day")
							.toString()
							.indexOf(
									"\"favor_day_" + day + "\":\"favor_day_"
											+ day + "\"") > 0) {
				if (row.get("favor_price") != null) {
					return row.get("favor_price").toString();
				}
			}
		}

		return price;
	}

	public static String getFoodPrice(String id, String buffetid, String rest_id) {
		List<Map<String, Object>> foodList = (List) foodPriceMap.get(rest_id
				+ "_" + id);
		if (foodList == null || foodList.size() == 0) {
			// 获得数据字典控制器
			SpecialOfferController c = SpringContextHolder
					.getBean("specialOfferController");
			String sql = "SELECT food_food.id food_id, food_food.rest_id, food_specialoffer.begin_date, food_specialoffer.end_date, food_specialoffer.begin_time, food_specialoffer.end_time, food_specialoffer.favor_week, food_specialoffer.favor_day, food_food.price, food_specialoffer_item.favor_price FROM food_food LEFT JOIN food_specialoffer_item ON food_food.id = food_specialoffer_item.food_id  AND food_specialoffer_item.delete_flag='0' LEFT JOIN food_specialoffer ON food_specialoffer.id = food_specialoffer_item.spec_id AND food_specialoffer.delete_flag='0' WHERE 1 = 1 AND food_food.delete_flag='0'  AND food_food.id = '"
					+ id + "' ORDER BY food_specialoffer_item.favor_price";
			foodList = c.queryBySql(sql, null);
			foodPriceMap.put(rest_id + "_" + id, foodList);
		}

		String price = "0";
		for (int i = 0; i < foodList.size(); i++) {
			Map<String, Object> row = foodList.get(i);
			Date date = new Date();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String day = new SimpleDateFormat("d").format(date);
			String now = new SimpleDateFormat("HH:mm:00").format(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(Calendar.DAY_OF_WEEK);
			week = week-1;
			if(week==0)	{
				week=7;
			}
			if (i == 0) {
				price = row.get("price").toString();
			}

			if (today.compareTo(row.get("begin_date").toString()) >= 0
					&& today.compareTo(row.get("end_date").toString()) <= 0
					&& now.compareTo(row.get("begin_time").toString()) >= 0
					&& now.compareTo(row.get("end_time").toString()) <= 0
					&& row.get("favor_week")
							.toString()
							.indexOf(
									"\"favor_week_" + week + "\":\"favor_week_"
											+ week + "\"") > 0
					&& row.get("favor_day")
							.toString()
							.indexOf(
									"\"favor_day_" + day + "\":\"favor_day_"
											+ day + "\"") > 0) {
				if (row.get("favor_price") != null) {
					return row.get("favor_price").toString();
				}
			}
		}

		return price;
	}
	
	public static String[] getFoodPriceInfo(String id, String buffetid, String rest_id,double huiyuanzhekou) {
		//foodPriceMap是菜品价格的一个缓存对象，其中以"餐厅id(rest_id)_菜品id(food_id)"为key存储
		//从缓存中读取菜品信息
		List<Map<String, Object>> foodList = (List) foodPriceMap.get(rest_id + "_" + id +"youhui");
		//如果在缓存中没有查询到则到数据库中进行查找
		if (foodList == null || foodList.size() == 0) {
			//获得控制器
			SpecialOfferController c = SpringContextHolder.getBean("specialOfferController");
			//food_food.id food_id					菜品id
			//food_food.rest_id						餐厅id
			//food_specialoffer.begin_date			开始日期
			//food_specialoffer.end_date			结束日期
			//food_specialoffer.begin_time			开始时间
			//food_specialoffer.end_time			结束时间
			//food_specialoffer.favor_week			优惠星期
			//food_specialoffer.favor_day			优惠日期
			//food_food.price						原价
			//food_specialoffer_item.favor_price	优惠价
			//is_discount							会员是否优惠
			String sql = "SELECT food_food.id food_id, food_food.rest_id, food_specialoffer.begin_date, food_specialoffer.end_date, food_specialoffer.begin_time, food_specialoffer.end_time, food_specialoffer.favor_week, food_specialoffer.favor_day, food_food.price, food_specialoffer_item.favor_price, food_foodtype.is_discount FROM food_food LEFT JOIN food_foodtype ON food_foodtype.id=food_food.type_id LEFT JOIN food_specialoffer_item ON food_food.id = food_specialoffer_item.food_id  AND food_specialoffer_item.delete_flag='0' LEFT JOIN food_specialoffer ON food_specialoffer.id = food_specialoffer_item.spec_id AND food_specialoffer.delete_flag='0' WHERE 1 = 1 AND food_food.delete_flag='0'  AND food_food.id = '" + id + "' ORDER BY food_specialoffer_item.favor_price";
			//查询
			foodList = c.queryBySql(sql, null);
			
			sql="SELECT food_buffet_item.* FROM food_buffet_item WHERE food_buffet_item.delete_flag='0' AND food_buffet_item.rest_id='"+rest_id+"'";
			List buffetItemList = c.queryBySql(sql, null);
			Map buffetItemMap = new HashMap();
			for(int i=0;i<buffetItemList.size();i++){
				Map row = (Map)buffetItemList.get(i);
				String food_id=row.get("food_id").toString();
				String buffet_id=row.get("buffet_id").toString();
				if(buffetItemMap.get(food_id)==null){
					buffetItemMap.put(food_id, buffet_id);
				}
				else{
					buffetItemMap.put(food_id, (buffetItemMap.get(food_id)+","+buffet_id));
				}
			}
			
			for(int i=0;i<foodList.size();i++){
				Map row = foodList.get(i);
				String price=(String)row.get("price");
				String favor_price=(String)row.get("favor_price");
				if(price==null || price.equals("")){
					price="0";
				}
				if(favor_price==null || favor_price.equals("")){
					favor_price="0";
				}
				if(Float.parseFloat(price)==Float.parseFloat(favor_price)){
					row.put("is_favor", "0");
				}
				else{
					row.put("is_favor", "1");
				}
				row.put("buffet", buffetItemMap.get(row.get("id")));
			}
			//缓存
			foodPriceMap.put(rest_id + "_" + id+"youhui", foodList);
		}

		//价格
		String price = "0";
		String[] priceInfo = {"","","","","",""};
		//遍历优惠条件
		for (int i = 0; i < foodList.size(); i++) {
			Map<String, Object> row = foodList.get(i);
			//取得当前日期
			Date date = new Date();
			//取得当前年月日
			String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//取得当前日
			String day = new SimpleDateFormat("d").format(date);
			//取得当前时间
			String now = new SimpleDateFormat("HH:mm:00").format(date);
			//取得当前星期
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(Calendar.DAY_OF_WEEK);
			week = week-1;
			if(week<=0)	{
				week=7;
			}
			
			//如果是第一条记录，先记录原始价格
			if (i == 0) {
				price = row.get("price").toString();
				priceInfo[0]=row.get("price").toString();
				priceInfo[1]="0";
				priceInfo[2]="0";
				priceInfo[3]=row.get("is_discount").toString().equals("")?"0":row.get("is_discount").toString();
				priceInfo[5]=row.get("buffet")==null?"":row.get("buffet").toString();
			}

			//比较条件
			if (today.compareTo(row.get("begin_date").toString()) >= 0
					&& today.compareTo(row.get("end_date").toString()) <= 0
					&& now.compareTo(row.get("begin_time").toString()) >= 0
					&& now.compareTo(row.get("end_time").toString()) <= 0
					&& row.get("favor_week").toString().indexOf("\"favor_week_" + week + "\":\"favor_week_" + week + "\"") > 0
					&& row.get("favor_day").toString().indexOf("\"favor_day_" + day + "\":\"favor_day_" + day + "\"") > 0) {
				if (row.get("favor_price") != null && !row.get("favor_price").equals("") && (Float.parseFloat(price)>Float.parseFloat(row.get("favor_price").toString()))) {
					priceInfo[1]=row.get("favor_price").toString();
					priceInfo[2]=row.get("is_favor").toString();
				}
			}
			
			if(priceInfo[2].equals("0") && priceInfo[3].equals("1")){
				priceInfo[4]=String.valueOf(Double.parseDouble(priceInfo[0])*huiyuanzhekou);
			}
			else if(!priceInfo[1].equals("0")){
				priceInfo[4]=priceInfo[1];
			}
				
			else{
				priceInfo[4]=priceInfo[0];
			}
			
			//判断自助餐
			if(buffetid!=null && !buffetid.trim().toString().equals("")){
				if(priceInfo[5].indexOf(buffetid)>-1){
					priceInfo[4]="0";
				}
			}
		}

		return priceInfo;
	}
}