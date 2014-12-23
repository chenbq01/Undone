package cn.com.uwoa.business.authorize.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.com.uwoa.business.authorize.dao.UsersDao;
import cn.com.uwoa.business.authorize.po.Resources;
import cn.com.uwoa.business.authorize.po.Roles;
import cn.com.uwoa.business.authorize.po.Users;
import cn.com.uwoa.system.tools.*;
import cn.com.uwoa.business.authorize.util.*;
import cn.com.uwoa.global.security.SecurityHelper;

public class UsersDaoImpl implements UsersDao{

	private JdbcTemplate jdbcTemplate; 
    
    public JdbcTemplate getJdbcTemplate() { 
        return jdbcTemplate; 
    } 
    //注入 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    } 
    
	@Override
	public Users get(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from Users where id='" + id +"'";
		List<Users> users = jdbcTemplate.query(
				sql,
				new RowMapper<Users>() {
					@Override
					public Users mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Users us = new Users();
						
						us.setId(rs.getString("id"));
						us.setUserName(rs.getString("username"));
						us.setRestId(rs.getString("rest_id"));
						us.setTrueName(rs.getString("truename"));
						us.setUserType(rs.getString("usertype"));
						return us;
					}

				});
		Users us = new Users();
		if(users.size()>0)
		{
			us = users.get(0);
			//us.setRoles();
		}
		return null;
	}

	@Override
	public int save(Users entity) throws Exception {
		
		entity.setId(UUID.randomUUID());
		entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		entity.setCreator(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		//String 
		// TODO Auto-generated method stub
		
		Object[] obj ={entity.getId(),entity.getUserName(),entity.getPassword(),entity.getTrueName(),entity.getRestId(),entity.getEnable(),entity.getCreator(),entity.getLastModify(),entity.getCreateTime()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_INSERT_USERS,obj);
		
	}

	@Override
	public int update(Users entity) throws Exception {
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		Object[] obj ={entity.getUserName(),entity.getPassword(),entity.getTrueName(),entity.getRestId(),entity.getEnable(),entity.getLastModify(),entity.getId()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_UPDATE_USERS,obj);

	}

	@Override
	public int delete(Users entity) throws Exception {
		// TODO Auto-generated method stub
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_DELETE_USERS, entity.getId());
		
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Users findByName(String name) {
		
		Object[] obj ={name};
		List<Users> users = jdbcTemplate.query(
				"select id,username,password,rest_id,truename,usertype,enable from au_users where username=?",obj,
				new RowMapper<Users>() {
					@Override
					public Users mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Users us = new Users();
						
						us.setId(rs.getString("id"));
						us.setUserName(rs.getString("username"));
						us.setRestId(rs.getString("rest_id"));
						us.setTrueName(rs.getString("truename"));
						us.setUserType(rs.getString("usertype"));
						us.setPassword(rs.getString("password"));
						us.setEnable(rs.getInt("enable"));
					
						return us;
					}

				});
		Users us = null;
		if(users.size()>0)
		{
			us = users.get(0);
			us.setRoles(findUserRoles(us.getId()));
			//us.setRoles();
		}
		// TODO Auto-generated method stub
		return us;
	}
	
	
	public Set<Roles> findUserRoles(String userId) {
		String sql = "select * from au_roles where id in (select rid from au_users_roles where uid='" + userId +"')";
		List<Roles> ls = jdbcTemplate.query(
				sql,
				new RowMapper<Roles>() {
					@Override
					public Roles mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Roles us = new Roles();
						us.setId(rs.getString("id"));
						us.setName(rs.getString("name"));
						us.setEnable(rs.getInt("enable"));
						return us;
					}

				});
		Set roles = new HashSet<Roles>();
		for(int i=0;i<ls.size();i++){
			Roles rs = ls.get(i);
			rs.setResources(findRolesResources(rs.getId()));
			roles.add(rs);
		}
		return roles;
	}
	public Set<Resources> findRolesResources(String roleId) {

		List<Resources> ls = jdbcTemplate.query(
				"select id,name,type,priority,url,memo from au_resources where id in (select rsid from au_roles_resources where rid='" + roleId +"')",
				new RowMapper<Resources>() {
					@Override
					public Resources mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Resources res = new Resources();
						res.setId(rs.getString("id"));
						res.setName(rs.getString("name"));
						res.setType(rs.getInt("type"));
						res.setPriority(rs.getInt("priority"));
						res.setMemo(rs.getString("memo"));
						res.setUrl(rs.getString("url"));
						return res;
					}

				});
		Set res = new HashSet<Resources>();
		res.addAll(ls);
		return res;
	}
	
	@Override
	public int addRestAdmin(Users users) {
		
		users.setId(UUID.randomUUID());
		users.setCreateTime(new Timestamp(System.currentTimeMillis()));
		users.setCreator(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		users.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		//String 
		// TODO Auto-generated method stub
		
		Object[] obj ={users.getId(),users.getUserName(),users.getPassword(),users.getTrueName(),users.getRestId(),users.getUserType(),users.getEnable(),users.getCreator(),users.getLastModify(),users.getCreateTime()};
		
		
		this.jdbcTemplate.update(IAuthorizeConstants.SQL_INSERT_USERS,obj);
		//此处写死一角色ID
		Object[] objnew ={UUID.randomUUID(),"93a5cb74e26b4ff2a1dc802fccabdc10",users.getId(),SecurityHelper.getLoginUserId(),SecurityHelper.getLoginUserId(),null,null};
		this.jdbcTemplate.update(IAuthorizeConstants.SQL_INSERT_USERROLE,objnew);
		return 0;
	}

}
