package org.season.autumn.repository;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.season.autumn.domain.Role;
import org.season.autumn.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/root-context.xml",
		"classpath:/META-INF/spring/jms/jms-activemq.xml",
		"classpath:/META-INF/spring/data/data-jpa.xml", })
@TransactionConfiguration(defaultRollback = false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void testSave() {
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setEnabled(true);
		user.setDescription("description");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		Set<Role> roleSet = new HashSet<Role>();
		Role role = new Role();
		role.setRolename("rolename");
		role.setDescription("description");
		roleSet.add(role);
		user.setRoles(roleSet);
		user = userRepository.save(user);
		System.out.println("ID:" + user.getId());
	}

	@Test
	@Transactional
	public void testDelete() {
		userRepository.delete(Long.valueOf("3"));
	}

	@Test
	@Transactional
	public void testFindOneInOneSession() {
		User user1 = userRepository.findOne(Long.valueOf("1"));
		User user2 = userRepository.findOne(Long.valueOf("1"));
		Assert.assertSame(user1, user2);
	}

	@Test
	public void testFindOneInMultiSession() {
		System.out.println("+++++第一次查询开始+++++");
		User user1 = userRepository.findOne(Long.valueOf("1"));
		System.out.println("+++++第一次查询结束+++++");
		System.out.println("+++++第二次查询开始+++++");
		User user2 = userRepository.findOne(Long.valueOf("1"));
		System.out.println("+++++第二次查询结束+++++");
		Assert.assertSame(user1, user2);
	}

	@Test
	@Transactional
	public void testFindByUsernameInOneSession() {
		User user1 = userRepository.findByUsername("guest");
		User user2 = userRepository.findByUsername("guest");
		Assert.assertSame(user1, user2);
	}

	@Test
	public void testFindByUsernameInMultiSession() {
		System.out.println("+++++第一次查询开始+++++");
		User user1 = userRepository.findByUsername("guest");
		System.out.println("+++++第一次查询结束+++++");
		System.out.println("+++++第二次查询开始+++++");
		User user2 = userRepository.findByUsername("guest");
		System.out.println("+++++第二次查询结束+++++");
		Assert.assertSame(user1, user2);
	}

}
