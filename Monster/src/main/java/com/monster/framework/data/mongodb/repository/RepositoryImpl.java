package com.monster.framework.data.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.monster.framework.data.mongodb.domain.Tree;


@Component
public class RepositoryImpl implements Repository<Tree> {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Tree> getAllObjects() {
		return mongoTemplate.findAll(Tree.class);
	}

	public void saveObject(Tree tree) {
		mongoTemplate.insert(tree);
	}

	public Tree getObject(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				Tree.class);
	}

	public WriteResult updateObject(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), Tree.class);
	}

	public void deleteObject(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Tree.class);
	}

	public void createCollection() {
		if (!mongoTemplate.collectionExists(Tree.class)) {
			mongoTemplate.createCollection(Tree.class);
		}
	}

	public void dropCollection() {
		if (mongoTemplate.collectionExists(Tree.class)) {
			mongoTemplate.dropCollection(Tree.class);
		}
	}
}