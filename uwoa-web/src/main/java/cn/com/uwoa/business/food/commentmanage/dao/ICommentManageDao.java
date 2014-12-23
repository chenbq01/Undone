package cn.com.uwoa.business.food.commentmanage.dao;

import cn.com.uwoa.system.frame.po.TablePo;

/**
 * 菜品管理/菜品点评管理  - 数据库持久层接口
 * @author liyue
 */
public interface ICommentManageDao {
	/**
	 * 审核
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	public int audit(TablePo dataPo);
}
