package cn.com.uwoa.business.authorize.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Resources {
	// Fields

	private String id;
	private String url;
	private Integer priority;
	private Integer type;
	private String name;
	private String memo;
	private Set roles = new HashSet(0);
	
	private String creator;
	private String lastmodify;
	private Timestamp createtime;
	private Timestamp lastmodifytime;
	
	// Constructors

	/** default constructor */
	public Resources() {
	}
	
	
	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
	}



	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Set getRoles() {
		return roles;
	}



	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	public String getCreator(){
		return this.creator;
	}
	public void setCreator(String creator)	{
		this.creator = creator;
	}
	public String getLastModify(){
		return this.lastmodify;
	}
	public void setLastModify(String lastmodify)	{
		this.lastmodify = lastmodify;
	}
	public Timestamp getCreateTime(){
		return this.createtime;
	}
	public void setCreateTime(Timestamp createtime){
		this.createtime = createtime;
	}
	public Timestamp getLastModifyTime(){
		return this.lastmodifytime;
	}
	public void setLastModifyTime(Timestamp lastmodifytime){
		this.lastmodifytime = lastmodifytime;
	}
	


}
