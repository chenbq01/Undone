package cn.com.uwoa.business.authorize.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.com.uwoa.business.authorize.dao.RolesDao;
import cn.com.uwoa.business.authorize.po.Roles;
import cn.com.uwoa.business.authorize.po.Users;
import cn.com.uwoa.business.authorize.util.IAuthorizeConstants;
import cn.com.uwoa.system.tools.UUID;

public class RolesDaoImpl implements RolesDao{

	private JdbcTemplate jdbcTemplate; 
    
    public JdbcTemplate getJdbcTemplate() { 
        return jdbcTemplate; 
    } 
    //注入 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    } 
	@Override
	public Roles get(String id) {
		String sql = "select * from roles where id='" + id +"'";
		List<Roles> roles = jdbcTemplate.query(
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
		Roles us = new Roles();
		if(roles.size()>0)
		{
			us = roles.get(0);
			//us.setRoles();
		}
		return null;
		
	}

	@Override
	public int save(Roles entity) throws Exception {
		entity.setId(UUID.randomUUID());
		entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		entity.setCreator(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		//String 
		// TODO Auto-generated method stub
		
		Object[] obj ={entity.getId(),entity.getName(),entity.getEnable(),entity.getCreator(),entity.getLastModify(),entity.getCreateTime()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_INSERT_ROLES,obj);
	}

	@Override
	public int update(Roles entity) throws Exception {
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		Object[] obj ={entity.getName(),entity.getEnable(),entity.getLastModify(),entity.getId()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_UPDATE_ROLES,obj);
		
	}

	@Override
	public int delete(Roles entity) throws Exception {
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_DELETE_ROLES, entity.getId());
	}

	@Override
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
		Set roles = new HashSet<Roles>(0);
		roles.addAll(ls);
		return roles;
	}

	@Override
	public List<Roles> findAll() {
		String sql = "select * from au_roles ";
		return jdbcTemplate.query(
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
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
