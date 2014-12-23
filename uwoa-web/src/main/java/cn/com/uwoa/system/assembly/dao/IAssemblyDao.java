package cn.com.uwoa.system.assembly.dao;

import java.util.List;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 李鑫
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
public interface IAssemblyDao {

	/**
	 * @param data
	 * @return
	 */
	public List findTargetBySource(String Conditions,String tableName,String sourceField,String targetField,String condition);

	/**
	 * @param data
	 * @param tableName
	 * @param sourceField
	 * @param targetField
	 * @return
	 */
	public List findTargetBySource(String data, String tableName, String sourceField, String[] targetField,String condition);

	
}
