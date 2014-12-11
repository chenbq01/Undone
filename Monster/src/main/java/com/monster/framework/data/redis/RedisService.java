package com.monster.framework.data.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public void save(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}

	public String read(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bKey = redisTemplate.getStringSerializer()
						.serialize(key);
				if (connection.exists(bKey)) {
					byte[] bValue = connection.get(bKey);
					return redisTemplate.getStringSerializer().deserialize(
							bValue);
				}
				return null;
			}
		});
	}

	public void delete(final String key) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize(
						key));
				return null;
			}
		});
	}

}