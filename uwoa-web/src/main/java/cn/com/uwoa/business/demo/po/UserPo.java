package cn.com.uwoa.business.demo.po;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPo {

	@Min(1) 
	private int id;
	
	@NotNull(message = "不能为空")  
	@Size(min=1,message = "")  
	private String name;

	@NotNull(message = "密码不能为空")  
	@Size(min=1,message = "")  
	
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 

}
