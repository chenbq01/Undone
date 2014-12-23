package cn.com.uwoa.business.demo.dao;

import java.util.List;

import cn.com.uwoa.business.demo.po.LogPo;
import cn.com.uwoa.business.demo.po.UserPo;

public interface ITemplateDao {

	public List<UserPo> getAllUsers();
	
	public UserPo getUserByID(String userid);
	
	public List<LogPo> getLogFromSolrByLogMsg(String msg);
	
}
