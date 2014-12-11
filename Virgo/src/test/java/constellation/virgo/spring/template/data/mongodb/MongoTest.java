package constellation.virgo.spring.template.data.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import constellation.virgo.spring.template.data.mongodb.domain.Tree;
import constellation.virgo.spring.template.data.mongodb.repository.Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-base.xml",
		"classpath:/META-INF/spring/data/mongodb/data-mongodb.xml",
		"classpath:/META-INF/spring/data/redis/data-redis.xml",
		"classpath:/META-INF/spring/jms/jms-activemq.xml",
		"classpath:/META-INF/spring/data/jpa/data-jpa.xml" })
public class MongoTest {

	@Autowired
	private Repository<Tree> repository;

	@Test
	public void crud() {

		// cleanup collection before insertion
		repository.dropCollection();

		// create collection
		repository.createCollection();

		repository.saveObject(new Tree("1", "Apple Tree", 10));

		System.out.println("1. " + repository.getAllObjects());

		repository.saveObject(new Tree("2", "Orange Tree", 3));

		System.out.println("2. " + repository.getAllObjects());

		System.out.println("Tree with id 1" + repository.getObject("1"));

		repository.updateObject("1", "Peach Tree");

		System.out.println("3. " + repository.getAllObjects());

		repository.deleteObject("2");

		System.out.println("4. " + repository.getAllObjects());

	}

}
