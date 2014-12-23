package cn.com.uwoa.business.authorize.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Roles {
	private String id;
	private Integer enable;
	private String name;
	private Set roles = new HashSet(0);
	private Set<Resources> resources = new HashSet<Resources>(0);
	
	private String creator;
	private String lastmodify;
	private Timestamp createtime;
	private Timestamp lastmodifytime;
	
	
	/** default constructor */
	public Roles() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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



	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
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
