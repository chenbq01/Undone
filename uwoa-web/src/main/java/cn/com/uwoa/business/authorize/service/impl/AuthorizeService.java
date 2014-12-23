package cn.com.uwoa.business.authorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.authorize.dao.UsersDao;
import cn.com.uwoa.business.authorize.po.Users;
import cn.com.uwoa.business.authorize.service.*;
import cn.com.uwoa.system.frame.dao.IFrameDao;
@Service
public class AuthorizeService implements IAuthorizeService {

	
	/**
	 * 数据库访问层
	 */
	@Autowired
	protected IFrameDao frameDao;
	
	/**
	 * 数据库访问层
	 */
	@Autowired
	protected UsersDao userDao;
	
	
	@Override
	public int addRestAdminUser(String userName, String password,String rest_id) {
		
		Users u = userDao.findByName(userName);
		if(u!=null)
			return 1;
		
		Users users = new Users();
		users.setRestId(rest_id);
		users.setEnable(1);
		users.setPassword(cn.com.uwoa.system.tools.UUID.Md5(password));
		users.setUserName(userName);
		users.setUserType("2");
		return userDao.addRestAdmin(users);

	}
	
}
