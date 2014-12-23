package cn.com.uwoa.business.demo.service;

import java.util.List;

import cn.com.uwoa.business.demo.po.LogPo;
import cn.com.uwoa.business.demo.po.UserPo;

public interface ITemplateService {
	
	public List<UserPo> getAllUsers();
	
	public UserPo getUserByID(String userid);
	
	public List<LogPo> getLogFromSolrByLogMsg(String msg);
		
}
