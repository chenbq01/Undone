package cn.com.uwoa.business.authorize.util;

public class IAuthorizeConstants {

	
	public final static String SQL_INSERT_USERS = "insert into au_users(id,username,password,truename,rest_id,usertype,enable,creator,last_modify,create_time)values(?,?,?,?,?,?,?,?,?,?)";
	public final static String SQL_UPDATE_USERS = "update au_users set username=?,password=?,truename=?,rest_id=?,usertype=?,enable=?,last_modify=? where id=?";
	public final static String SQL_DELETE_USERS = "delete from au_users where id=?";
	public final static String SQL_SELECT_USERS = "select id,username,password,truename,rest_id,usertype,enable,creator,last_modify,create_time,last_modify_time from au_users ";
	
	public final static String SQL_INSERT_ROLES = "insert into au_roles(id,name,enable,creator,last_modify,create_time,last_modify_time) values(?,?,?,?,?,?,?)";
	public final static String SQL_UPDATE_ROLES = "update au_roles set name=?,enable=?,last_modify=? where id=? ";
	public final static String SQL_DELETE_ROLES = "delete from au_roles where id=?";
	public final static String SQL_SELECT_ROLES = "select id,name,enable,creator,last_modify,create_time,last_modify_time from au_roles ";
	
	public final static String SQL_INSERT_RESOURCES = "insert into au_resources(id,name,type,priority,url,memo,last_modify,create_time,last_modify_time) values(?,?,?,?,?,?,?)";
	public final static String SQL_UPDATE_RESOURCES = "update au_resources set name=?,type=?,priority=?,url=?,memo=?,last_modify=? where id=? ";
	public final static String SQL_DELETE_RESOURCES = "delete from au_resources where id=?";
	public final static String SQL_SELECT_RESOURCES = "";
	
	public final static String SQL_INSERT_USERROLE = "insert into au_users_roles(id,rid,uid,creator,last_modify,create_time,last_modify_time) values(?,?,?,?,?,?,?)";
	public final static String SQL_DELETE_USERROLE_FROM_USER = "delete from au_users_roles where uid=?";
	public final static String SQL_DELETE_USERROLE_FROM_ROLE = "delete from au_users_roles where rid=?";

	
	public final static String SQL_INSERT_ROLERESOURCE = "insert into au_roles_resources(id,rid,rsid) values(?,?,?)";
	public final static String SQL_UPDATE_ROLERESOURCE = "delete from au_roles_resources where rid=?";
	public final static String SQL_DELETE_ROLERESOURCE = "delete from au_roles_resources where rsid=?";
	
	
	

	
	
	
}
