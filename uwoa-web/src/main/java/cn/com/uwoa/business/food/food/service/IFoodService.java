package cn.com.uwoa.business.food.food.service;

import java.util.List;
import java.util.Map;

import cn.com.uwoa.system.frame.po.PagePo;

/**
 * 菜品管理/菜品维护管理 - 业务服务层接口
 * @author liyue
 */
public interface IFoodService {

	public int importExcel(List<Map<String, String>> data);
}