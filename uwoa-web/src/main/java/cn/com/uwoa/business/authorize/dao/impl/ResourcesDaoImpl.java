package cn.com.uwoa.business.authorize.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.com.uwoa.business.authorize.dao.ResourcesDao;
import cn.com.uwoa.business.authorize.po.Resources;
import cn.com.uwoa.business.authorize.po.Roles;
import cn.com.uwoa.business.authorize.util.IAuthorizeConstants;
import cn.com.uwoa.system.tools.UUID;

public class ResourcesDaoImpl implements ResourcesDao {

	private JdbcTemplate jdbcTemplate; 
    
    public JdbcTemplate getJdbcTemplate() { 
        return jdbcTemplate; 
    } 
    //注入 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    } 
	
	@Override
	public Resources get(String id) {
		String sql = "select * from Resources where id='" + id +"'";
		List<Resources> resources = jdbcTemplate.query(
				sql,
				new RowMapper<Resources>() {
					@Override
					public Resources mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Resources us = new Resources();
						
						us.setId(rs.getString("id"));
						us.setName(rs.getString("name"));
						us.setUrl(rs.getString("url"));
						us.setMemo(rs.getString("memo"));
						us.setPriority(rs.getInt("priority"));
						us.setType(rs.getInt("type"));
						return us;
					}

				});
		Resources us = new Resources();
		if(resources.size()>0)
		{
			us = resources.get(0);
			//us.setRoles();
		}
		return null;
	}

	@Override
	public int save(Resources entity) throws Exception {
		entity.setId(UUID.randomUUID());
		entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		entity.setCreator(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		//String 
		// TODO Auto-generated method stubinsert into roles(id,name,type,priority,url,memo,
		
		Object[] obj ={entity.getId(),entity.getName(),entity.getMemo(),entity.getCreator(),entity.getLastModify(),entity.getCreateTime()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_INSERT_RESOURCES,obj);
	}

	@Override
	public int update(Resources entity) throws Exception {
		entity.setLastModify(cn.com.uwoa.global.security.SecurityHelper.getLoginUserId());
		Object[] obj ={entity.getName(),entity.getType(),entity.getPriority(),entity.getUrl(),entity.getMemo(),entity.getLastModify(),entity.getId()};
		
		
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_UPDATE_RESOURCES,obj);
	}

	@Override
	public int delete(Resources entity) throws Exception {
		return this.jdbcTemplate.update(IAuthorizeConstants.SQL_DELETE_RESOURCES, entity.getId());
	}

	@Override
	public List<Resources> findAll() {
		
		return jdbcTemplate.query(
				"select id,name,type,priority,url,memo from au_resources",
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
		
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
