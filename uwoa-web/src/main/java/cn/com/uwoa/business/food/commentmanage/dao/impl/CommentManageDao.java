package cn.com.uwoa.business.food.commentmanage.dao.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.business.food.commentmanage.dao.ICommentManageDao;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;

/**
 * 基础框架 - 数据库持久层实现
 * @author liyue
 */
@Repository
public class CommentManageDao implements ICommentManageDao {
	/**
	 * 数据库模版对象
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 审核
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int audit(TablePo dataPo){
		// 查询条件串
		StringBuffer where = new StringBuffer();

		// 遍历查询条件对象
		Iterator<FieldPo> it = dataPo.getFields().values().iterator();
		while (it.hasNext()) {
			FieldPo fieldPo = it.next();
			// 处理特殊字段
			if (fieldPo.getName().equals("ids")) {
				//ids直接IN
				where.append(fieldPo.getValue().equals("") ? "" : " AND id IN ('" + fieldPo.getValue().replaceAll(",", "','") + "')");
			}
			else {
				// 处理特殊操作符
				if (fieldPo.getOperator().equals("LIKE")) {
					where.append(fieldPo.getValue().equals("") ? "" : " AND " + fieldPo.getName() + " " + fieldPo.getOperator() + " ('%" + fieldPo.getValue() + "%')");
				}
				else {
					where.append(fieldPo.getValue().equals("") ? "" : " AND " + fieldPo.getName() + fieldPo.getOperator() + " ('" + fieldPo.getValue() + "')");
				}
			}
		}

		//SQL语句
		String sql="UPDATE " + dataPo.getName() + " SET audit_flag='1',audit_time=now(),auditer='"+ SecurityHelper.getLoginUserId() +"' WHERE 1=1 " + where.toString();
		
		//新增并返回
		return jdbcTemplate.update(sql.toString());
	}
}
