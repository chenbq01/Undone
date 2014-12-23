package cn.com.uwoa.business.restaurant.restaurantinfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.restaurant.restaurantinfo.service.IRestaurantInfoService;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 餐厅设置/餐厅介绍 - 业务服务层实现类
 * @author liyue
 */
@Service
public class RestaurantInfoService extends FrameService implements IRestaurantInfoService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("rest_restaurant"));
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
		return "";
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("rest_restaurant"));
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
}