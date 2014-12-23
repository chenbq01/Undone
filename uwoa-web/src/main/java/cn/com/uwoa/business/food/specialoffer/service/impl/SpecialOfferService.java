package cn.com.uwoa.business.food.specialoffer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.business.food.specialoffer.service.ISpecialOfferService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 菜品管理/限时特价 - 业务服务层实现类
 * @author liyue
 */
@Service
public class SpecialOfferService extends FrameService implements ISpecialOfferService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("food_specialoffer"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("key")!=null){
					fields.get("key").setOperator("LIKE");
				}
				//修改名称的操作符
				if(fields.get("name")!=null){
					fields.get("name").setOperator("LIKE");
				}
			}
		});
		
		//返回
		return returnMap;
	}

	/**
	 * 查询子表
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> querySub(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("food_specialoffer_item"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("spec_id")!=null){
					fields.get("spec_id").setOperator("IN");
				}
			}
		});

		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "food_food", "food_id", "food_name", "id", "food_name", null);
			AssemblyHelper.assembly((List)returnMap.get("data"), "food_food", "food_id", "food_price", "id", "price", null);
			AssemblyHelper.assembly((List)returnMap.get("data"), "food_food", "food_id", "unit", "id", "unit", null);
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "unit", "unit_name", "value", "name", " AND dictionary_key='FOOD_UNIT' ");
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
		SpecialOfferHelper.foodPriceMap.clear();
		//调用框架查询方法
		return super.add(dataMap,  new TablePo("food_specialoffer"){
				/**
				 * 用户自定义
				 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
				 */
				@Override
				public void userDefined() {
					for(int i=1;i<=7;i++){
						fields.remove("favor_week_"+i);
					}
					for(int i=1;i<=31;i++){
						fields.remove("favor_day_"+i);
					}
					fields.put("begin_time", new FieldPo("begin_time","=",(fields.get("begin_time_h").getValue())+":"+(fields.get("begin_time_m").getValue())));
					fields.put("end_time", new FieldPo("end_time","=",(fields.get("end_time_h").getValue())+":"+(fields.get("end_time_m").getValue())));
					fields.remove("begin_time_h");
					fields.remove("begin_time_m");
					fields.remove("end_time_h");
					fields.remove("end_time_m");
				}
		} , new SubTablePo("food_specialoffer_item", "spec_id"));
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		SpecialOfferHelper.foodPriceMap.clear();
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("food_specialoffer"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				for(int i=1;i<=7;i++){
					fields.remove("favor_week_"+i);
				}
				for(int i=1;i<=31;i++){
					fields.remove("favor_day_"+i);
				}
				fields.put("begin_time", new FieldPo("begin_time","=",(fields.get("begin_time_h").getValue())+":"+(fields.get("begin_time_m").getValue())));
				fields.put("end_time", new FieldPo("end_time","=",(fields.get("end_time_h").getValue())+":"+(fields.get("end_time_m").getValue())));
				fields.remove("begin_time_h");
				fields.remove("begin_time_m");
				fields.remove("end_time_h");
				fields.remove("end_time_m");
			}
		} , new SubTablePo("food_specialoffer_item", "spec_id"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		SpecialOfferHelper.foodPriceMap.clear();
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("food_specialoffer"));
	}
}
