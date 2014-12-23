package cn.com.uwoa.business.authorize.po;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class Users {
	// Fields

	private String id;
	private Integer enable;
	private String password;
	private String username;
	private Set<Roles> roles = new HashSet<Roles>(0);
	private String rest_id;
	private String usertype;
	private String truename;
	
	private String creator;
	private String lastmodify;
	private Timestamp createtime;
	private Timestamp lastmodifytime;
	
	
	
	
	// Constructors

	/** default constructor */
	public Users() {
		this.id="";
	}

	// Property accessors

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getRestId(){
		return this.rest_id;
	}
	public void setRestId(String rest_id){
		this.rest_id = rest_id;
	}
	public String getUserType(){
		return this.usertype;
	}
	public void setUserType(String usertype){
		this.usertype = usertype;
	}
	public String getTrueName(){
		return this.truename;
	}
	public void setTrueName(String truename){
		this.truename = truename;
	}
	


	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
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
