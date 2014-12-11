package constellation.virgo.spring.template.data.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import constellation.virgo.spring.template.data.jpa.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-base.xml",
		"classpath:/META-INF/spring/data/mongodb/data-mongodb.xml",
		"classpath:/META-INF/spring/data/redis/data-redis.xml",
		"classpath:/META-INF/spring/jms/jms-activemq.xml",
		"classpath:/META-INF/spring/data/jpa/data-jpa.xml" })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreateNewAccount() {
		userService.createNewAccount("wwd", "wwd", 1000);
	}
}
