package constellation.virgo.spring.template.data.redis;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import constellation.virgo.spring.template.data.redis.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-base.xml",
		"classpath:/META-INF/spring/data/mongodb/data-mongodb.xml",
		"classpath:/META-INF/spring/data/redis/data-redis.xml",
		"classpath:/META-INF/spring/jms/jms-activemq.xml",
		"classpath:/META-INF/spring/data/jpa/data-jpa.xml" })
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void crud() {
		// -------------- Save ---------------
		redisService.save("key1", "value1");

		// ---------------Read ---------------
		Assert.assertEquals("value1", redisService.read("key1"));
		// --------------Update ------------
		redisService.save("key1", "value2");
		Assert.assertEquals("value2", redisService.read("key1"));

		// --------------Delete ------------
		redisService.delete("key1");
		Assert.assertNull(redisService.read("key1"));

	}

}
