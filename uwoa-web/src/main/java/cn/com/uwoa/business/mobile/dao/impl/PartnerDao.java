package cn.com.uwoa.business.mobile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.business.mobile.dao.IPartnerDao;
import cn.com.uwoa.global.cache.CacheContentBuilder;
import cn.com.uwoa.global.cache.CacheService;
import cn.com.uwoa.global.cache.CacheService.KEY_PREFIX;
import cn.com.uwoa.global.cache.CacheService.TIME;
import cn.com.uwoa.global.cache.impl.CacheServiceImpl;

@Repository("mobilePartnerDao")
public class PartnerDao implements IPartnerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Set<String> getAllPartnerCode() {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		Set<String> cachePartnerCodeSet = cache.get(KEY_PREFIX.PARTNER_CODE,
				"", TIME.INDEFINITE, params,
				new CacheContentBuilder<Set<String>>() {
					@Override
					public Set<String> cacheContentBuilder(
							Map<String, Object> params) {

						Set<String> set = ((JdbcTemplate) params
								.get("jdbcTemplate")).query("SELECT r.rest_code FROM rest_restaurant r where r.delete_flag = '0'",
								new ResultSetExtractor<Set<String>>() {

									@Override
									public Set<String> extractData(ResultSet rs)
											throws SQLException,
											DataAccessException {
										HashSet<String> set = new HashSet<String>();
										while (rs.next()) {
											set.add(rs.getString("rest_code"));
										}
										return set;
									}

								});
						return set;
					}
				});
		return cachePartnerCodeSet;
	}

	@Override
	public Map<String, Object> getPartnerProfileByCode(String partnercode) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnercode", partnercode);

		Map<String, Object> profile = cache.get(KEY_PREFIX.PARTNER_INFO, "_"
				+ partnercode, TIME.INDEFINITE, params,
				new CacheContentBuilder<Map<String, Object>>() {
					@Override
					public Map<String, Object> cacheContentBuilder(
							Map<String, Object> params) {
						return ((JdbcTemplate) params.get("jdbcTemplate"))
								.queryForMap("SELECT r.* FROM rest_restaurant r WHERE r.rest_code='"+params.get("partnercode")+"' AND r.delete_flag = '0'");
					}
				});
		return profile;
	}

}
