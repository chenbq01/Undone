package cn.com.uwoa.business.authorize.dao;

import java.util.List;

import cn.com.uwoa.business.authorize.po.Resources;
import cn.com.uwoa.global.dao.BaseDao;

public interface ResourcesDao  extends BaseDao<Resources> {

	List<Resources> findAll();

}
