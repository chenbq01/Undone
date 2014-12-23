package cn.com.uwoa.business.demo.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.demo.dao.ITemplateDao;
import cn.com.uwoa.business.demo.po.LogPo;
import cn.com.uwoa.business.demo.po.UserPo;
import cn.com.uwoa.business.demo.service.ITemplateService;
import cn.com.uwoa.global.cache.CacheService;
import cn.com.uwoa.global.cache.CacheService.KEY_PREFIX;
import cn.com.uwoa.global.cache.impl.CacheServiceImpl;

@Service
public class TemplateService implements ITemplateService {

	@Autowired
	private ITemplateDao templateDao;

	@Override
	public List<UserPo> getAllUsers() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		// 此处为valid示例
		UserPo user = new UserPo();
		user.setId(0);
		user.setPassword("password");
		user.setName("");
		Set<ConstraintViolation<UserPo>> constraintViolations = validator
				.validate(user);

		for (ConstraintViolation<UserPo> constraintViolation : constraintViolations) {
			System.out.println(constraintViolation.getMessage());
		}

		return templateDao.getAllUsers();
	}

	@Override
	public UserPo getUserByID(String userid) {
		return templateDao.getUserByID(userid);
	}

	@Override
	public List<LogPo> getLogFromSolrByLogMsg(String msg) {
		return templateDao.getLogFromSolrByLogMsg(msg);
	}

	public void work() {
		CacheService cache = CacheServiceImpl.getInstance();
		KEY_PREFIX[] groups = KEY_PREFIX.values();
		for (int i = 0; i < groups.length; i++) {
			cache.removegroup(groups[i].getValue());
		}
	}

}
