package cn.com.uwoa.business.food.commentmanage.service;

import java.util.Map;

/**
 * 菜品管理/菜品点评管理 - 业务服务层接口
 * @author liyue
 */
public interface ICommentManageService {
	/**
	 * 审核
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	public int audit(Map<String,Object> dataMap);
}
