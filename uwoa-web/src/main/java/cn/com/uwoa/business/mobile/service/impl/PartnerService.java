package cn.com.uwoa.business.mobile.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.mobile.dao.IPartnerDao;
import cn.com.uwoa.business.mobile.service.IPartnerService;

@Service("mobilePartnerService")
public class PartnerService implements IPartnerService {

	@Autowired
	private IPartnerDao partnerDao;

	@Override
	public boolean isExistedPartnerCode(String partnercode) {
		Set<String> partnerCodeSet = partnerDao.getAllPartnerCode();
		return partnerCodeSet.contains(partnercode);
	}

	@Override
	public Map<String, Object> getPartnerProfileByCode(String partnercode) {
		return partnerDao.getPartnerProfileByCode(partnercode);
	}

}
