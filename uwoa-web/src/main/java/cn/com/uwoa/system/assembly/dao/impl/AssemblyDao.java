package cn.com.uwoa.system.assembly.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.system.assembly.dao.IAssemblyDao;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 李鑫
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
@Repository
public class AssemblyDao implements IAssemblyDao {
	/**
	 * 数据库模版对象
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * com.use.iso9000.assembly.dao.IAssemblyDao#findTargetBySource(java.lang
	 * .String)
	 */
	public List findTargetBySource(String Conditions, String tableName,
			String sourceField, String targetField, String condition) {
		String sql = "SELECT " + sourceField + " source," + targetField
				+ " target from " + tableName + " WHERE " + sourceField
				+ " IN(" + Conditions + ") ";
		if (condition != null) {
			sql += condition;
		}

		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String column[] = new String[2];
				column[0] = rs.getString("source");
				column[1] = rs.getString("target");
				return column;
			}
		});
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * com.use.iso9000.assembly.dao.IAssemblyDao#findTargetBySource(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public List findTargetBySource(String Conditions, String tableName,
			String sourceField, String[] targetField, String condition) {
		String tf = "";
		for (int i = 0; i < targetField.length; i++) {
			tf = tf + "," + targetField[i] + " target" + String.valueOf(i);
		}
		String sql = "SELECT " + sourceField + " source" + tf + " from "
				+ tableName + " WHERE " + sourceField + " IN(" + Conditions
				+ ") ";
		if (condition != null) {
			sql += condition;
		}

		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String column[] = new String[rs.getMetaData().getColumnCount()];
				column[0] = rs.getString("source");
				for (int j = 1; j < column.length; j++) {
					column[j] = rs.getString("target" + String.valueOf(j - 1));
				}
				return column;
			}
		});
	}

}
