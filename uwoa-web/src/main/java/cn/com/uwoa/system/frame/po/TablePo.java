package cn.com.uwoa.system.frame.po;

import java.util.Map;

/**
 * 基础框架 - 表Po
 * @author liyue
 */
public class TablePo {
	/**
	 * 表名
	 */
	protected String name;
	
	/**
	 * 字段信息
	 */
	protected Map<String,FieldPo> fields;
	
	/**
	 * 构造函数
	 */
	private TablePo(){
	}
	
	/**
	 * 构造函数
	 * @param name 表名
	 */
	public TablePo(String name){
		this.name = name;
	}
	
	/**
	 * 获得名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得字段信息
	 * @return 字段信息
	 */
	public Map<String,FieldPo> getFields() {
		return fields;
	}

	/**
	 * 设置字段信息
	 * @param fields 字段信息
	 */
	public void setFields(Map<String,FieldPo> fields) {
		this.fields = fields;
	}
	
	/**
	 * 用户自定义
	 */
	public void userDefined(){
		
	}
}
