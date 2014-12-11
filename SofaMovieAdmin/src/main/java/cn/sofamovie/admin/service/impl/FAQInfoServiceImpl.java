package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.FAQInfo;
import cn.sofamovie.admin.repository.FAQInfoRepository;
import cn.sofamovie.admin.service.FAQInfoService;

@Service("faqInfoService")
public class FAQInfoServiceImpl implements FAQInfoService {

	@Autowired
	private FAQInfoRepository faqInfoRepository;

	@Override
	@Transactional
	public FAQInfo saveFAQInfo(FAQInfo faqInfo) {
		return faqInfoRepository.save(faqInfo);
	}

	@Override
	public List<FAQInfo> findAllFAQsByCategoryID(String categoryid) {
		return faqInfoRepository.findFAQsByCategoryID(Long.valueOf(categoryid));
	}

	@Override
	@Transactional
	public boolean removeFAQ(String id) {
		return faqInfoRepository.deleteImageByID(Long.valueOf(id)) > 0;
	}

	@Override
	@Transactional
	public boolean modifyFAQByID(String id, String question, String answer,
			String orderno) {
		return faqInfoRepository.modifyFAQByID(Long.valueOf(id), question,
				answer, Integer.valueOf(orderno)) > 0;
	}

	@Override
	public FAQInfo getinfo(String id) {
		return faqInfoRepository.findOne(Long.valueOf(id));
	}

}
