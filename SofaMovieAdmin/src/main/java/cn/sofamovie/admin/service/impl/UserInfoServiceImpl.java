package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.UserInfo;
import cn.sofamovie.admin.repository.UserInfoRepository;
import cn.sofamovie.admin.service.UserInfoService;
import cn.sofamovie.admin.util.StringHelper;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	@Transactional
	public UserInfo createUser(String username, String password, Integer isadmin) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(StringHelper.encryptStringByMD5(password));
		userInfo.setIsadmin(isadmin);
		return userInfoRepository.save(userInfo);
	}

	@Override
	public boolean existsUsername(String username) {
		List<UserInfo> list = userInfoRepository.findByUsername(username);
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean resetPassword(String id) {
		return userInfoRepository.modifyPasswordByID(
				StringHelper.encryptStringByMD5("123456"), Long.parseLong(id)) > 0;
	}

	@Override
	@Transactional
	public boolean deleteUser(String id) {
		return userInfoRepository.deleteUserInfoByID(Long.parseLong(id)) > 0;
	}

	@Override
	public List<UserInfo> findUserByUsernameAndPassword(String username,
			String password) {
		//System.out.println(StringHelper.encryptStringByMD5(password));
		return userInfoRepository.findByUsernameAndPassword(username,
				StringHelper.encryptStringByMD5(password));

	}

	@Override
	@Transactional
	public boolean modifyPasswordByUserID(String password, String id) {
		return userInfoRepository.modifyPasswordByID(
				StringHelper.encryptStringByMD5(password), Long.parseLong(id)) > 0;
	}

	@Override
	public List<UserInfo> findNonAdministratorUser() {
		return userInfoRepository.findNonAdministratorUser();
	}

	@Override
	@Transactional
	public boolean enableUserAccount(String id) {
		return userInfoRepository.modifyDeleteflagByID(0, Long.parseLong(id)) > 0;
	}

	@Override
	@Transactional
	public boolean disableUserAccount(String id) {
		return userInfoRepository.modifyDeleteflagByID(1, Long.parseLong(id)) > 0;
	}

}
