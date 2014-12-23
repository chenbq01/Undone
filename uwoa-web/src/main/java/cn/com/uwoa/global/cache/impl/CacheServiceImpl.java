package cn.com.uwoa.global.cache.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.global.cache.CacheContentBuilder;
import cn.com.uwoa.global.cache.CacheService;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

@Service
public class CacheServiceImpl extends GeneralCacheAdministrator implements
		CacheService {

	private static final long serialVersionUID = -4950916198444378845L;

	private static CacheServiceImpl instance = new CacheServiceImpl();

	private CacheServiceImpl() {
	}

	public static CacheServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void remove(String key) {
		this.flushEntry(key);
	}

	@Override
	public void removegroup(String group) {
		this.flushGroup(group);
	}

	@Override
	public void put(String key, Object value) {
		this.putInCache(key, value);
	}

	@Override
	public Object get(String key, TIME time) {
		try {
			return this.getFromCache(key, time.getValue());
		} catch (NeedsRefreshException e) {
			this.cancelUpdate(key);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(KEY_PREFIX prefix, String key, TIME time,
			Map<String, Object> params,
			CacheContentBuilder<T> cacheContentBuilder) {
		T value = null;
		boolean updated = false;
		try {
			// Get from the cache
			value = (T) this.getFromCache(prefix.getValue().toString() + key,
					time.getValue());
		} catch (NeedsRefreshException nre) {
			try {
				// Get the value (probably by calling an EJB)
				value = cacheContentBuilder.cacheContentBuilder(params);
				// Store in the cache
				if (value != null) {
					this.putInCache(prefix.getValue().toString() + key, value,
							new String[] { prefix.getValue() });
					updated = true;
				}
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					this.cancelUpdate(prefix.getValue().toString() + key);
				}
			}
		}
		return value;
	}

}
