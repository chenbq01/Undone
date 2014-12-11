package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.UserInfo;

public interface UserInfoRepository extends Repository<UserInfo, Long> {

	public UserInfo save(UserInfo userInfo);

	@Query("from UserInfo u where u.username = :username and u.deleteflag = 0")
	public List<UserInfo> findByUsername(@Param("username") String username);

	@Query("from UserInfo u where u.username = :username and u.password = :password and u.deleteflag = 0")
	public List<UserInfo> findByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);

	@Modifying
	@Query("update UserInfo u set u.password = :password where u.id = :id and u.deleteflag = 0")
	public int modifyPasswordByID(@Param("password") String password,
			@Param("id") Long id);

	@Modifying
	@Query("update UserInfo u set u.deleteflag = 1 where u.id = :id")
	public int deleteUserInfoByID(@Param("id") Long id);

	@Query("from UserInfo u where u.isadmin = 0")
	public List<UserInfo> findNonAdministratorUser();

	@Modifying
	@Query("update UserInfo u set u.deleteflag = :deleteflag where u.id = :id")
	public int modifyDeleteflagByID(@Param("deleteflag") Integer deleteflag,
			@Param("id") Long id);

}
