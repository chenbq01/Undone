package cn.sofamovie.admin.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-base.xml",
		"classpath:/META-INF/spring/data/jpa/data-jpa.xml" })
public class UserInfoServiceTest {

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void testCreateUser() {
		userInfoService.createUser("wwd", "wwd", 0);
	}

	@Test
	public void testResetPassword() {
		Assert.assertTrue(userInfoService.resetPassword("1"));
	}

	@Test
	public void testExistsUsername() {
		Assert.assertTrue(userInfoService.existsUsername("wwd"));
	}

	@Test
	public void testDeleteUser() {
		Assert.assertTrue(userInfoService.deleteUser("1"));
	}

}
