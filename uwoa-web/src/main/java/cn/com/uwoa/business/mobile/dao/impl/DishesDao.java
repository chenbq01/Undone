package cn.com.uwoa.business.mobile.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.business.mobile.dao.IDishesDao;
import cn.com.uwoa.global.cache.CacheContentBuilder;
import cn.com.uwoa.global.cache.CacheService;
import cn.com.uwoa.global.cache.CacheService.KEY_PREFIX;
import cn.com.uwoa.global.cache.CacheService.TIME;
import cn.com.uwoa.global.cache.impl.CacheServiceImpl;
import cn.com.uwoa.system.tools.UUID;

@Repository("mobileDishesDao")
public class DishesDao implements IDishesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getSpecialtyList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		List<Map<String, Object>> cacheSpecialtyList = cache.get(
				KEY_PREFIX.SPECIALTY_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num FROM food_food f where f.rest_id = '"
										+ params.get("partnerid")
										+ "' AND f.is_special = '1' AND f.delete_flag = '0' ORDER BY f.order_num");
						return list;
					}
				});
		calculatePrice(cacheSpecialtyList, "id", "price", buffetid, partnerid);
		return cacheSpecialtyList;
	}

	@Override
	public List<Map<String, Object>> getNewList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		List<Map<String, Object>> cacheNewList = cache.get(KEY_PREFIX.NEW_LIST,
				"_" + partnercode, TIME.INDEFINITE, params,
				new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num FROM food_food f where f.rest_id = '"
										+ params.get("partnerid")
										+ "' AND f.is_new = '1' AND f.delete_flag = '0'  ORDER BY f.order_num");
						return list;
					}
				});
		calculatePrice(cacheNewList, "id", "price", buffetid, partnerid);
		return cacheNewList;
	}

	@Override
	public List<Map<String, Object>> getPromotionList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnercode", partnercode);
		List<Map<String, Object>> cachePromotionList = cache.get(
				KEY_PREFIX.PROMOTION_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num FROM food_food f where f.rest_id = (SELECT r.id FROM rest_restaurant r WHERE r.rest_code = '"
										+ params.get("partnercode")
										+ "') AND f.delete_flag = '0'  ORDER BY f.order_num");
						return list;
					}
				});
		// 遍历所有菜，调用计算特价的方法。如果价格不一致，则保留，不一致则舍去。
		Map<String, Object> map = null;
		for (Iterator<Map<String, Object>> it = cachePromotionList.iterator(); it
				.hasNext();) {
			map = it.next();
			BigDecimal tj = new BigDecimal(SpecialOfferHelper.getFoodPrice(
					(String) map.get("id"), buffetid, partnerid));
			BigDecimal yj = (BigDecimal) map.get("price");
			if (tj.equals(yj)) {
				it.remove();
			} else {
				map.put("tprice", tj);
			}
		}

		return cachePromotionList;
	}

	@Override
	public List<Map<String, Object>> getRecommendList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		List<Map<String, Object>> cacheRecommendList = cache.get(
				KEY_PREFIX.RECOMMEND_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num FROM food_food f where f.rest_id = '"
										+ params.get("partnerid")
										+ "' AND f.delete_flag = '0' ORDER BY f.vote_num DESC,f.order_num  LIMIT 10");
						return list;
					}
				});
		calculatePrice(cacheRecommendList, "id", "price", buffetid, partnerid);
		return cacheRecommendList;
	}

	@Override
	public List<Map<String, Object>> getMenuList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		List<Map<String, Object>> cacheMenuList = cache.get(
				KEY_PREFIX.MENU_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT t.type_name,f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num,f.is_weigh FROM food_food f,food_foodtype t where f.type_id = t.id AND f.rest_id = '"
										+ params.get("partnerid")
										+ "' AND f.delete_flag = '0' AND t.delete_flag = '0' ORDER BY t.order_num,t.id,f.order_num,f.id");
						return list;
					}
				});
		calculatePrice(cacheMenuList, "id", "price", buffetid, partnerid);
		return cacheMenuList;
	}

	@Override
	public List<Map<String, Object>> getCommentList(String dishesid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("dishesid", dishesid);
		List<Map<String, Object>> cacheCommentList = cache.get(
				KEY_PREFIX.COMMENT_LIST, "_" + dishesid, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT c.comm_content,c.comm_time FROM food_comment c WHERE c.food_id = '"
										+ params.get("dishesid")
										+ "' AND c.audit_flag = '1' AND c.delete_flag = '0' ORDER BY c.audit_time DESC LIMIT 10");
						return list;
					}
				});
		return cacheCommentList;
	}

	@Override
	public Map<String, Object> getDishInfo(String partnercode,
			String partnerid, String dishesid, String buffetid) {
		List<Map<String, Object>> cacheMenuList = getMenuList(partnercode,
				partnerid, buffetid);
		Iterator<Map<String, Object>> itr = cacheMenuList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> dishinfo = itr.next();
			if (dishinfo.get("id").equals(dishesid)) {
				return dishinfo;
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getFavouriteList(String partnerid,
			String mobileno, String buffetid) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num,of.fcount ");
		sbf.append("FROM food_food f,food_foodtype t,(SELECT i.food_id fid,count(i.id) fcount FROM ord_order o,ord_order_item i,member m ");
		sbf.append("WHERE ");
		sbf.append("o.id = i.order_id AND o.member_id =m.id AND m.mobile_num = '");
		sbf.append(mobileno);
		sbf.append("' AND o.rest_id = '");
		sbf.append(partnerid);
		sbf.append("' GROUP BY i.food_id) of WHERE f.id = of.fid AND f.type_id = t.id AND t.is_drinks = '0' AND t.is_staple = '0' AND f.delete_flag = '0' GROUP BY of.fcount DESC");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sbf
				.toString());
		calculatePrice(list, "id", "price", buffetid, partnerid);
		return list;
	}

	@Override
	public int addDishComment(String dishid, String orderid, String remark,
			String partnercode) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("INSERT INTO food_comment ( ");
		sbf.append("id, ");
		sbf.append("order_id, ");
		sbf.append("food_id, ");
		sbf.append("comm_content, ");
		sbf.append("comm_time, ");
		sbf.append("audit_flag, ");
		sbf.append("rest_id, ");
		sbf.append("creator, ");
		sbf.append("create_time, ");
		sbf.append("last_modify, ");
		sbf.append("last_modify_time, ");
		sbf.append("delete_flag) ");
		sbf.append("VALUES('");
		sbf.append(UUID.randomUUID());
		sbf.append("','");
		sbf.append(orderid);
		sbf.append("','");
		sbf.append(dishid);
		sbf.append("','");
		sbf.append(remark);
		sbf.append("',NOW(),'0',(SELECT r.id FROM rest_restaurant r WHERE r.rest_code = '");
		sbf.append(partnercode);
		sbf.append("'),'MEMBER',NOW(),'MEMBER',NOW(),'0')");
		return jdbcTemplate.update(sbf.toString());
	}

	@Override
	public int addRecommend(String dishid, String orderid, String partnercode) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("INSERT INTO food_recommend ( ");
		sbf.append("id, ");
		sbf.append("food_id, ");
		sbf.append("order_id, ");
		sbf.append("reco_time, ");
		sbf.append("creator, ");
		sbf.append("create_time, ");
		sbf.append("last_modify, ");
		sbf.append("last_modify_time, ");
		sbf.append("rest_id, ");
		sbf.append("delete_flag) ");
		sbf.append("VALUES('");
		sbf.append(UUID.randomUUID());
		sbf.append("','");
		sbf.append(dishid);
		sbf.append("','");
		sbf.append(orderid);
		sbf.append("',NOW(),'MEMBER',NOW(),'MEMBER',NOW(),(SELECT r.id FROM rest_restaurant r WHERE r.rest_code = '");
		sbf.append(partnercode);
		sbf.append("'),'0')");
		return jdbcTemplate.update(sbf.toString());
	}

	private void calculatePrice(List<Map<String, Object>> list,
			String dishidname, String pricename, String buffetid,
			String partnerid) {
		// 遍历所有菜，调用计算特价的方法。

		Map<String, Object> map = null;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			map.put(pricename,
					new BigDecimal(SpecialOfferHelper.getFoodPrice(
							(String) map.get(dishidname), buffetid, partnerid)));

		}

	}

	@Override
	public List<Map<String, Object>> getBuffetList(String partnercode,
			String partnerid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		List<Map<String, Object>> cacheBuffetList = cache.get(
				KEY_PREFIX.BUFFET_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT b.id,b.buffet_name,b.pic,b.price,b.memo FROM food_buffet b WHERE b.rest_id = '"
										+ params.get("partnerid")
										+ "' AND b.delete_flag = '0' ORDER BY b.price");
						return list;
					}
				});
		return cacheBuffetList;
	}

	@Override
	public List<Map<String, Object>> getBuffetItemList(String partnercode,
			String partnerid, String buffetid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnerid", partnerid);
		params.put("buffetid", buffetid);
		List<Map<String, Object>> cacheBuffetItemList = cache.get(
				KEY_PREFIX.BUFFETITEM_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num FROM food_buffet_item i,food_food f WHERE i.food_id = f.id AND i.buffet_id = '"
										+ params.get("buffetid")
										+ "' AND i.rest_id = '"
										+ params.get("partnerid")
										+ "' AND i.delete_flag = '0' AND f.delete_flag = '0'");
						return list;
					}
				});
		// 重新计算刷新价格
		return cacheBuffetItemList;
	}

	@Override
	public int updateFoodVoteCount(String dishid) {
		StringBuffer sbf = new StringBuffer(500);
		sbf.append("UPDATE food_food f SET f.vote_num = f.vote_num+1 WHERE f.id = '");
		sbf.append(dishid);
		sbf.append("'");
		return jdbcTemplate.update(sbf.toString());
	}

}
