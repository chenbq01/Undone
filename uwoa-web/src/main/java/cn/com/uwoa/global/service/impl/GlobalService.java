package cn.com.uwoa.global.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.system.frame.service.impl.FrameService;
import cn.com.uwoa.global.service.IGlobalService;

@Service
public class GlobalService  extends FrameService implements IGlobalService{

	@Override
	public Map<String, Object> query(Map<String, Object> condMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int edit(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeNoQuery(String sql) {
		// TODO Auto-generated method stub
		return frameDao.executeNonQuery(sql);
		
	}

	@Override
	public String getData(String sql) {
		return frameDao.selectBySql(sql,null).get(0).get(0).toString();
		
	}
	
	
	
	

}
