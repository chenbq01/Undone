package cn.com.uwoa.system.frame.po;

/**
 * 基础框架 - 字段Po
 * @author liyue
 */
public class FieldPo {
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 运算符
	 */
	private String operator;
	
	/**
	 * 值
	 */
	private String value;

	/**
	 * 构造函数
	 */
	public FieldPo(){
	}
	
	/**
	 * 构造函数
	 */
	public FieldPo(String name,String operator,String value){
		this.name=name;
		this.operator=operator;
		this.value=value;
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
	 * 获得运算符
	 * @return 运算符
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置运算符
	 * @param operator 运算符
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获得值
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置值
	 * @param value 值
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
