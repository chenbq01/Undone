package cn.com.uwoa.global.dao;

import java.util.List;


public interface BaseDao<T> {
	T get(String id);
    int save(T entity) throws Exception;
    int update(T entity) throws Exception;
    int delete(T entity) throws Exception;
    
    List<T> findAll();
    int count();

}
