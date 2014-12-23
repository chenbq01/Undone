package cn.com.uwoa.business.authorize.dao;

import java.util.Set;

import cn.com.uwoa.business.authorize.po.Roles;
import cn.com.uwoa.global.dao.BaseDao;

public interface RolesDao  extends BaseDao<Roles> {
	public Set<Roles> findUserRoles(String userId);
}