package cn.com.uwoa.system.frame.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.frame.dao.IFrameDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.tools.UUID;

/**
 * 基础框架 - 数据库持久层实现
 * @author liyue
 */
@Repository
public class FrameDao implements IFrameDao {
	/**
	 * 数据库模版对象
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询
	 * @param condPo 查询条件对象
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	@Override
	public List<Map<String, Object>> select(TablePo condPo,PagePo pagePo) {
		return select("*", condPo,pagePo);
	}
	
	/**
	 * 查询
	 * @param field 查询字段
	 * @param condPo 查询条件对象
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	@Override
	public List<Map<String,Object>> select(String field,TablePo condPo,PagePo pagePo) {
		// 查询条件串
		StringBuffer where = new StringBuffer();
		// 排序条件串
		StringBuffer orderBy = new StringBuffer();
		
		// 遍历查询条件对象
		Iterator<FieldPo> it = condPo.getFields().values().iterator();
		while (it.hasNext()) {
			FieldPo fieldPo = it.next();
			// 处理特殊字段
			if (fieldPo.getName().equals("ids")) {
				//ids直接IN
				where.append(fieldPo.getValue().equals("") ? "" : " AND `id` IN ('" + fieldPo.getValue().replaceAll(",", "','") + "')");
			}
			else if (fieldPo.getName().equals("rest_id")) {
				where.append(fieldPo.getValue()==null || fieldPo.getValue().equals("") || fieldPo.getValue().equals("null") ? "" : " AND (`rest_id` IN ('" + fieldPo.getValue().replaceAll(",", "','") + "') OR `rest_id` IS NULL OR `rest_id` = '')");
			}
			else if (fieldPo.getName().equals("orderBy")) {
				orderBy.append(" ORDER BY "+fieldPo.getValue());
			}
			else {
				// 处理特殊操作符
				if (fieldPo.getOperator().equals("LIKE")) {
					where.append(fieldPo.getValue().equals("") ? "" : " AND `" + fieldPo.getName() + "` " + fieldPo.getOperator() + " ('%" + fieldPo.getValue() + "%')");
				}
				else if (fieldPo.getOperator().equals("IN")) {
					where.append(fieldPo.getValue().equals("") ? "" : " AND `" + fieldPo.getName() + "` " + fieldPo.getOperator() + " ('" + fieldPo.getValue() + "')");
				}
				else {
					where.append(fieldPo.getValue().equals("") ? "" : " AND `" + fieldPo.getName() + "` " + fieldPo.getOperator() + " ('" + fieldPo.getValue() + "')");
				}
			}
		}
		//如果没设置删除标记的条件，默认查0
		if(condPo.getFields().get("delete_flag")==null){
			where.append(" AND delete_flag='0'");
		}

		//SQL语句
		String sql="SELECT "+ field +" FROM " + condPo.getName() + " WHERE 1=1 " + where.toString();
		if(orderBy.toString().equals("")){
			orderBy.append(" ORDER BY create_time DESC");
		}
		sql+=orderBy.toString();

		if(pagePo!=null){
			sql+=" LIMIT "+(pagePo.getPage()-1)*pagePo.getPageRow()+","+pagePo.getPageRow();
		}
		
		System.out.println(sql);
		// 查询并返回结果集
//		return jdbcTemplate.queryForList(sql);
		return jdbcTemplate.query(sql,
				new RowMapper<Map<String,Object>>() {

					@Override
					public  Map mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResultSetMetaData rsmd = rs.getMetaData();
						Map row = new HashMap();
						for(int i=1;i<=rsmd.getColumnCount();i++){
							row.put(rsmd.getColumnName(i),rs.getString(i)==null?"":rs.getString(i));
						}
						return row;
					}

				});
	}
	
	/**
	 * 查询
	 * @param sql 查询语句
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	@Override
	public List<Map<String,Object>> selectBySql(String sql,PagePo pagePo) {
		if(pagePo!=null){
			sql+=" LIMIT "+(pagePo.getPage()-1)*pagePo.getPageRow()+","+pagePo.getPageRow();
		}
		
		System.out.println(sql);
		// 查询并返回结果集
		return jdbcTemplate.query(sql,
				new RowMapper<Map<String,Object>>() {

					@Override
					public  Map mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResultSetMetaData rsmd = rs.getMetaData();
						Map row = new HashMap();
						for(int i=1;i<=rsmd.getColumnCount();i++){
							row.put(rsmd.getColumnName(i),rs.getString(i)==null?"":rs.getString(i));
						}
						return row;
					}

				});
	}
	
	/**
	 * 新增
	 * @param dataPo 数据对象
	 * @return 主键
	 */
	@Override
	public String insert(TablePo dataPo){
		//SQL语句
		StringBuffer sql = new StringBuffer();
		//值列表
		List<Object> valueList = new ArrayList<Object>();
		//值字符串
		StringBuffer valueChar = new StringBuffer();
		
		//拼装INSERT语句
		sql.append("INSERT INTO "+dataPo.getName()+ "(");
		// 遍历数据对象
		Iterator<FieldPo> it = dataPo.getFields().values().iterator();
		// 系统字段
		sql.append("`id`,`creator`,`create_time`,`last_modify`,`last_modify_time`");
		valueChar.append("?,?,?,?,?");
		String id = UUID.randomUUID();
		valueList.add(id);
		valueList.add(SecurityHelper.getLoginUserId());
		valueList.add(null);
		valueList.add(SecurityHelper.getLoginUserId());
		valueList.add(null);
		
		int i=0;
		while (it.hasNext()) {
			FieldPo fieldPo = it.next();
			// 处理条件字段
			if (fieldPo.getName().equals("id")) {
			}
			else{
				try{
				//拼装字段
				sql.append(",`"+fieldPo.getName()+"`");
				//拼装占位符
				valueChar.append(",?");
				//拼装值数组
				valueList.add(fieldPo.getValue().equals("")?null:fieldPo.getValue());
				}
				catch(Exception e){
					System.out.println(e);
				}
				//增加循环变量
				i++;
			}
		}
		//拼装INSERT语句
		sql.append(") VALUES ("+valueChar.toString()+")");
		
		System.out.println(sql.toString());
		//新增并返回
		jdbcTemplate.update(sql.toString(),valueList.toArray());
		
		return id;
	}
	
	/**
	 * 修改
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int update(TablePo dataPo){
		//SQL语句
		StringBuffer sql = new StringBuffer();
		//删除条件串
		StringBuffer where = new StringBuffer();
		//值列表
		List<Object> valueList = new ArrayList<Object>();

		//拼装UPDATE语句
		sql.append("UPDATE "+dataPo.getName()+ " SET last_modify='"+SecurityHelper.getLoginUserId()+"' ");
		// 遍历数据对象
		Iterator<FieldPo> it = dataPo.getFields().values().iterator();
		int i=0;
		while (it.hasNext()) {
			FieldPo fieldPo = it.next();
			// 处理条件字段
			if (fieldPo.getName().equals("id")) {
				//id直接=
				where.append(fieldPo.getValue().equals("") ? "" : " AND id = '" + fieldPo.getValue() + "'");
			}
			else if (fieldPo.getName().equals("ids")) {
				//ids直接IN
				where.append(fieldPo.getValue().equals("") ? "" : " AND id IN (" + fieldPo.getValue() + ")");
			}
			else if (fieldPo.getName().equals("delete_flag")) {
				//ids直接IN
				where.append(fieldPo.getValue().equals("") ? "" : " AND delete_flag = '" + fieldPo.getValue() + "'");
			}
			else if (fieldPo.getName().equals("rest_id")) {
				//ids直接IN
				where.append(fieldPo.getValue().equals("") ? "" : " AND rest_id = '" + fieldPo.getValue() + "'");
			}
			else{
				//拼装字段
				sql.append(",`"+fieldPo.getName()+"`=?");
				//拼装值数组
				valueList.add(fieldPo.getValue().equals("")?null:fieldPo.getValue());
				//增加循环变量
				i++;
			}
		}
		
		//拼装UPDATE语句
		sql.append(" WHERE 1=1 "+where.toString());
		
		System.out.println(sql.toString());
		//修改并返回
		return jdbcTemplate.update(sql.toString(),valueList.toArray());
	}
	
	/**
	 * 删除
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(TablePo dataPo){
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
		String sql="UPDATE " + dataPo.getName() + " SET delete_flag='1' WHERE 1=1 " + where.toString();
		
		System.out.println(sql.toString());
		//新增并返回
		return jdbcTemplate.update(sql.toString());
	}
	
	/**
	 * 删除
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int deleteReal(TablePo dataPo){
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
		String sql="DELETE FROM " + dataPo.getName() + " WHERE 1=1 " + where.toString();
		
		System.out.println(sql.toString());
		//新增并返回
		return jdbcTemplate.update(sql.toString());
	}
	/**
	 * 直接执行sql语句，返回影响的行数
	 */
	public int executeNonQuery(String sql){
		
		return jdbcTemplate.update(sql);
	}
	public String getSingleData(String sql){
		
		//jdbcTemplate.query(sql, args, rch)
		List hm = jdbcTemplate.query(sql,
				new RowMapper<Map<String,Object>>() {
					@Override
					public  Map mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResultSetMetaData rsmd = rs.getMetaData();
						Map row = new HashMap();
						for(int i=1;i<=rsmd.getColumnCount();i++){
							row.put(rsmd.getColumnName(i),rs.getString(i)==null?"":rs.getString(i));
						}
						return row;
					}

				});
		Map m = (Map)hm.get(0);
		Set<String> key = m.keySet();     
		String sinfo = "";
		for (Iterator it = key.iterator(); it.hasNext();) {    
			
			String s = (String) it.next();           
			sinfo = m.get(s).toString();        
		}
		
		
		
		//hm.get
		return sinfo;
	}
}
