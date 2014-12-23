package cn.com.uwoa.business.food.food.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.food.food.service.IFoodService;
import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;
import cn.com.uwoa.system.tools.UUID;

/**
 * 菜品管理/菜品维护管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class FoodService extends FrameService implements IFoodService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("food_food"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("food_code")!=null){
					fields.get("food_code").setOperator("LIKE");
				}
				//修改名称的操作符
				if(fields.get("food_name")!=null){
					fields.get("food_name").setOperator("LIKE");
				}
				fields.put("orderBy", new FieldPo("orderBy", "=", "order_num,create_time"));
			}
		});
		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "food_foodtype", "type_id", "type_name", "id", "type_name", null);
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "unit", "unit_name", "value", "name", " AND dictionary_key='FOOD_UNIT' ");
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_new", "is_new_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_special", "is_special_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回
		return returnMap;
	}

	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 主键
	 */
	@Override
	public String add(Map<String, Object> dataMap) {
		//调用框架查询方法
		String id = super.add(dataMap,  new TablePo("food_food"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//是否上级发布
				fields.put("is_parent_public", new FieldPo("is_parent_public","","0"));
				//推荐票数
				fields.put("vote_num", new FieldPo("vote_num","","0"));
			}
		});
		return id;
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		SpecialOfferHelper.foodPriceMap.remove(SecurityHelper.getRestId()+"_"+dataMap.get("id"));
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("food_food"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		String[] idArray = ((String)dataMap.get("ids")).split(",");
		for(int i=0;i<idArray.length;i++){
			SpecialOfferHelper.foodPriceMap.remove(SecurityHelper.getRestId()+"_"+idArray[i]);
		}
		
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("food_food"));
	}
	
	/**
	 * 导入
	 */
	@Override
	public int importExcel(List<Map<String,String>> data) {
		Map<String,Object> condMap = new HashMap<String,Object>();
		Map foodMap = new HashMap();
		condMap.put("rest_id", SecurityHelper.getRestId());
		List lst = queryBySql("SELECT * FROM food_food WHERE 1=1  AND (`rest_id` IN ('"+SecurityHelper.getRestId()+"') OR `rest_id` IS NULL OR `rest_id` = '') AND delete_flag='0' ORDER BY order_num", null);
		for(int i=0;i<lst.size();i++){
			Map row = (Map)lst.get(i);
			foodMap.put(row.get("food_code"), row);
		}
		for(int i=0;i<data.size();i++){
			Map row = data.get(i);
			if(foodMap.get(row.get("food_code"))==null){
				String sql="INSERT INTO food_food (id,food_code,food_name,type_id,mnem_code,unit,price,is_new,is_special,is_weigh,order_num,food_intro,memo,creator,create_time,last_modify,last_modify_time,rest_id) VALUES ('"+UUID.randomUUID()+"','"+row.get("food_code")+"','"+row.get("food_name")+"','"+row.get("type_id")+"','"+row.get("mnem_code")+"','"+row.get("unit")+"','"+row.get("price")+"','"+row.get("is_new")+"','"+row.get("is_special")+"','"+row.get("is_weigh")+"','"+row.get("order_num")+"','"+row.get("food_intro")+"','"+row.get("memo")+"','"+SecurityHelper.getLoginUserId()+"',null,'"+SecurityHelper.getLoginUserId()+"',null,'"+SecurityHelper.getRestId()+"')";
				frameDao.executeNonQuery(sql);
			}
			else{
				String sql="UPDATE food_food SET food_code='"+row.get("food_code")+"',food_name='"+row.get("food_name")+"',type_id='"+row.get("type_id")+"',mnem_code='"+row.get("mnem_code")+"',unit='"+row.get("unit")+"',price='"+row.get("price")+"',is_new='"+row.get("is_new")+"',is_special='"+row.get("is_special")+"',is_weigh='"+row.get("is_weigh")+"',order_num='"+row.get("order_num")+"',food_intro='"+row.get("food_intro")+"',memo='"+row.get("memo")+"',last_modify='"+SecurityHelper.getLoginUserId()+"' WHERE id='"+((Map)foodMap.get(row.get("food_code"))).get("id")+"'";
				frameDao.executeNonQuery(sql);
			}
		}
		return 1;
	}
}
