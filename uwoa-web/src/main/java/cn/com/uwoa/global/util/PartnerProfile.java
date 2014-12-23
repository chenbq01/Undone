package cn.com.uwoa.global.util;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class PartnerProfile {

	@SuppressWarnings("unchecked")
	public static String getPartnerCode(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		return ((String) profile.get(SessionKey.KEY_PARTNERPROFILE_CODE));
	}

	@SuppressWarnings("unchecked")
	public static String getPartnerName(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		return ((String) profile.get(SessionKey.KEY_PARTNERPROFILE_NAME));
	}

	@SuppressWarnings("unchecked")
	public static String getPartnerID(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		return ((String) profile.get(SessionKey.KEY_PARTNERPROFILE_ID));
	}

	@SuppressWarnings("unchecked")
	public static String getPartnerVoteCount(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		return ((Integer) profile.get(SessionKey.KEY_PARTNERPROFILE_VOTE_COUNT))
				+ "";
	}
	
	@SuppressWarnings("unchecked")
	public static String getPartnerDisCountType(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		return ((String)profile.get(SessionKey.KEY_PARTNERPROFILE_DISCOUNT_TYPE));
	}
	
	@SuppressWarnings("unchecked")
	public static double getPartnerDisCountNum(HttpSession session) {
		Map<String, Object> profile = (Map<String, Object>) session
				.getAttribute(SessionKey.KEY_PARTNERPROFILE);
		Object o =profile.get(SessionKey.KEY_PARTNERPROFILE_DISCOUNT_NUM);
		if(o==null||o.toString().equals(""))
			return 0;
		return ((BigDecimal)o).doubleValue();
	}

}
