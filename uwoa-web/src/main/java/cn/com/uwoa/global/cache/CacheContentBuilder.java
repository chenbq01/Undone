package cn.com.uwoa.global.cache;

import java.util.Map;

public interface CacheContentBuilder<T> {

	T cacheContentBuilder(Map<String, Object> params);

}
