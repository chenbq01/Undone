package cn.com.uwoa.system.assembly.service;

import java.util.HashMap;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 李鑫
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
public interface IAssemblyService {
	/**
	 *根据ID获取表名称 
	 * 
	 * @param lData
	 * @param tableName
	 * @param sourceField
	 * @param targetField
	 * @return
	 */
	public HashMap findTargetBySource(String lData,String tableName,String sourceField,String targetField,String condition);

	/**
	 * 根据ID获取表名称 
	 * 
	 * @param findCondition
	 * @param tableName
	 * @param matchingField
	 * @param referenceField
	 * @return
	 */
	public HashMap findTargetBySource(String findCondition, String tableName, String matchingField, String[] referenceField,String condition);


}
