package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.UserInfo;

public interface UserInfoService {

	public UserInfo createUser(String username, String password, Integer isadmin);

	public boolean existsUsername(String username);

	public boolean resetPassword(String id);

	public boolean deleteUser(String id);

	public List<UserInfo> findUserByUsernameAndPassword(String username,
			String password);

	public boolean modifyPasswordByUserID(String password, String id);

	public List<UserInfo> findNonAdministratorUser();
	
	public boolean enableUserAccount(String id);
	
	public boolean disableUserAccount(String id);

}
