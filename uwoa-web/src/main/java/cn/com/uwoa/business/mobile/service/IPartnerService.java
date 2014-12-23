package cn.com.uwoa.business.mobile.service;

import java.util.Map;


public interface IPartnerService {

	public boolean isExistedPartnerCode(String partnercode);
	
	public Map<String, Object> getPartnerProfileByCode(String partnercode);
	
}
