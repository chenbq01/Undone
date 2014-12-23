package cn.com.uwoa.business.restaurant.memberdiscount.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.business.restaurant.memberdiscount.service.IMemberDiscountService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 餐厅设置/会员优惠菜品分类 - 业务服务层实现类
 * @author liyue
 */
@Service
public class MemberDiscountService extends FrameService implements IMemberDiscountService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("food_foodtype"));
		//手工处理
		try {
//			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_drinks", "is_drinks_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
//			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_drinks", "is_staple_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
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
		return String.valueOf(edit(dataMap));
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		SubTablePo subDataPo = new SubTablePo("food_foodtype", "rest_id","UPDATE");
		//保存子表
		Map sub = null;
		if(dataMap.get("sub")!=null){
			sub=(Map)dataMap.get("sub");
			dataMap.remove("sub");
		}
		else{
			sub=new HashMap();
		}
		
		//插入子表
		Iterator it = sub.values().iterator();
		if(subDataPo.getDelete_model().equals("UPDATE")){
			String iuIds = "";
			Map allSubDataMap = new HashMap();
			allSubDataMap.put(subDataPo.getParentId(), SecurityHelper.getRestId());
			allSubDataMap.put("is_discount", "0");
			subDataPo.setFields(autoAnalyzeQuery(allSubDataMap));
			frameDao.update(subDataPo);
			
			while(it.hasNext()){
				Map subDataMap = (Map)it.next();
				subDataMap.put(subDataPo.getParentId(), SecurityHelper.getRestId());
				subDataMap.put("is_discount", "1");
				//自动解析新增参数
				subDataPo.setFields(autoAnalyzeQuery(subDataMap));
				//用户自定义
				subDataPo.userDefined(dataMap);
				iuIds+="','"+subDataMap.get("id");
				frameDao.update(subDataPo);
			}
		}
		return 1;
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return 0;
	}
	
	/**
	 * 查询子表
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> querySub(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("food_food"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("rest_id")!=null){
					fields.get("rest_id").setOperator("IN");
				}
			}
		});

		//手工处理
		try {
//			AssemblyHelper.assembly((List)returnMap.get("data"), "food_food", "food_id", "food_name", "id", "food_name", null);
//			AssemblyHelper.assembly((List)returnMap.get("data"), "food_food", "food_id", "unit", "id", "unit", null);
//			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "unit", "unit_name", "value", "name", " AND dictionary_key='FOOD_UNIT' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回
		return returnMap;
	}
}