package cn.com.uwoa.business.food.commentmanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.food.commentmanage.dao.ICommentManageDao;
import cn.com.uwoa.business.food.commentmanage.service.ICommentManageService;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.frame.dao.IFrameDao;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 菜品管理/菜品点评管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class CommentManageService extends FrameService implements ICommentManageService {
	/**
	 * 数据库访问层
	 */
	@Autowired
	private ICommentManageDao commentManageDao;
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		StringBuffer sql = new StringBuffer("SELECT food_comment.*,food_food.food_name,food_foodtype.type_name FROM food_comment LEFT JOIN food_food ON food_food.id=food_comment.food_id LEFT JOIN food_foodtype ON food_foodtype.id=food_food.type_id WHERE food_comment.delete_flag='0' AND food_comment.audit_flag='0' ");
		sql.append(condMap.get("food_name")!=null && !condMap.get("food_name").equals("")?"AND food_food.food_name LIKE '%"+condMap.get("food_name")+"%' ":"" );
		sql.append(condMap.get("type_id")!=null && !condMap.get("type_id").equals("")?"AND food_food.type_id = '"+condMap.get("type_id")+"' ":"" );
		Map<String,Object> returnMap = super.query(condMap, sql.toString());
		
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
		return super.add(dataMap,  new TablePo("food_comment"));
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("food_comment"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("food_comment"));
	}
	
	/**
	 * 审核
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int audit(Map<String, Object> dataMap) {
		TablePo dataPo = new TablePo("food_comment");
		//自动解析修改参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		return commentManageDao.audit(dataPo);
	}
}
