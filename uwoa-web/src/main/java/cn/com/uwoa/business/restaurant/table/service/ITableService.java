package cn.com.uwoa.business.restaurant.table.service;

import java.util.Map;

/**
 * 餐厅设置/餐桌管理 - 业务服务层接口
 * @author liyue
 */
public interface ITableService {
	/**
	 * 批量新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	public int addBatch(Map<String, Object> dataMap);
}
