package cn.com.uwoa.business.authorize.dao;


import cn.com.uwoa.business.authorize.po.*;
import cn.com.uwoa.global.dao.*;

public interface UsersDao extends BaseDao <Users>{
	
	public Users findByName(String name);
	public int addRestAdmin(Users users);
}