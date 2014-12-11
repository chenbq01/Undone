package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.FAQInfo;

public interface FAQInfoService {

	public FAQInfo saveFAQInfo(FAQInfo faqInfo);

	public List<FAQInfo> findAllFAQsByCategoryID(String categoryid);

	public boolean removeFAQ(String id);

	public boolean modifyFAQByID(String id, String question, String answer,
			String orderno);
	
	public FAQInfo getinfo(String id);

}
