package cn.com.uwoa.system.frame.po;

import java.util.Map;

/**
 * 基础框架 - 子表Po
 * @author liyue
 */
public class SubTablePo extends TablePo {
	/**
	 * 父表ID
	 */
	protected String parent_id;
	
	/**
	 * 删除方式
	 */
	protected String delete_model;

	/**
	 * 构造函数
	 * @param name 表名
	 * @param parent_id 父表ID
	 */
	public SubTablePo(String name,String parent_id){
		super(name);
		this.parent_id = parent_id;
	}
	
	/**
	 * 构造函数
	 * @param name 表名
	 * @param parent_id 父表ID
	 * @param delete_model 删除方式
	 */
	public SubTablePo(String name,String parent_id,String delete_model){
		super(name);
		this.parent_id = parent_id;
		this.delete_model = delete_model;
	}
	
	/**
	 * 获得父表ID
	 * @return 父表ID
	 */
	public String getParentId() {
		return parent_id;
	}

	/**
	 * 设置父表ID
	 * @param parent_id 父表ID
	 */
	public void setParentId(String parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 设置删除方式
	 * @param delete_model 删除方式
	 */
	public void setDelete_model(String delete_model) {
		this.delete_model = delete_model;
	}
	
	/**
	 * 获得删除方式
	 * @return 删除方式
	 */
	public String getDelete_model() {
		if(delete_model==null){
			return "UPDATE";
		}
		return delete_model;
	}
	
	/**
	 * 用户自定义
	 * @param parent_data 父表数据
	 */
	public void userDefined(Map parent_data){
		
	}
}
