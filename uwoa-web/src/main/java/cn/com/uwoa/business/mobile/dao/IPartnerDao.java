package cn.com.uwoa.business.mobile.dao;

import java.util.Map;
import java.util.Set;

public interface IPartnerDao {
	
	public Set<String> getAllPartnerCode();
	
	public Map<String, Object> getPartnerProfileByCode(String partnercode);

}
