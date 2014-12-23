package cn.com.uwoa.business.mobile.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper;
import cn.com.uwoa.business.mobile.dao.IOrderDao;
import cn.com.uwoa.global.cache.CacheContentBuilder;
import cn.com.uwoa.global.cache.CacheService;
import cn.com.uwoa.global.cache.CacheService.KEY_PREFIX;
import cn.com.uwoa.global.cache.CacheService.TIME;
import cn.com.uwoa.global.cache.impl.CacheServiceImpl;
import cn.com.uwoa.system.tools.UUID;

@Repository("mobileOrderDao")
public class OrderDao implements IOrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> addToTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderno", orderno);
		params.put("dishid", dishid);
		params.put("dishname", dishname);
		params.put("num", num);
		params.put("unit", unit);
		params.put("price", price);
		params.put("addflag", false);
		Map<String, Object> cacheOrder = cache.get(KEY_PREFIX.TEMPORARY_ORDER,
				"_" + orderno, TIME.HOUR, params,
				new CacheContentBuilder<Map<String, Object>>() {
					@Override
					public Map<String, Object> cacheContentBuilder(
							Map<String, Object> params) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("orderno", params.get("orderno"));
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("dishid", params.get("dishid"));
						m.put("dishname", params.get("dishname"));
						m.put("num",
								Integer.valueOf((String) params.get("num")));
						m.put("unit", params.get("unit"));
						m.put("price", params.get("price"));
						list.add(m);
						map.put("orderdetail", list);
						params.put("addflag", true);
						return map;
					}
				});
		// 如果缓存里存在则合并添加到orderdetail中
		if (!((Boolean) params.get("addflag"))) {// 判断是否缓存里已经存在
			List<Map<String, Object>> list = (List<Map<String, Object>>) cacheOrder
					.get("orderdetail");
			// 用于临时存储匹配的对象，如果没有则一直为null
			Map<String, Object> tempMap = null;
			Iterator<Map<String, Object>> itr = list.iterator();
			while (itr.hasNext()) {
				Map<String, Object> dishinfo = itr.next();
				if (dishinfo.get("dishid").equals(dishid)) {
					tempMap = dishinfo;
					int tempNum = ((Integer) tempMap.get("num")).intValue()
							+ Integer.valueOf(num).intValue();
					tempMap.put("num", Integer.valueOf(tempNum));
					break;
				}
			}
			if (tempMap == null) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("dishid", params.get("dishid"));
				m.put("dishname", params.get("dishname"));
				m.put("num", Integer.valueOf((String) params.get("num")));
				m.put("unit", params.get("unit"));
				m.put("price", params.get("price"));
				list.add(m);
			}
		}
		return cacheOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> modifyTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price) {
		CacheService cache = CacheServiceImpl.getInstance();

		Map<String, Object> cacheOrder = (Map<String, Object>) cache.get(
				KEY_PREFIX.TEMPORARY_ORDER + "_" + orderno, TIME.HOUR);

		List<Map<String, Object>> list = (List<Map<String, Object>>) cacheOrder
				.get("orderdetail");
		Iterator<Map<String, Object>> itr = list.iterator();
		while (itr.hasNext()) {
			Map<String, Object> dishinfo = itr.next();
			if (dishinfo.get("dishid").equals(dishid)) {
				if (Integer.valueOf(num).intValue() > 0) {
					dishinfo.put("num", Integer.valueOf(num));
				} else {
					list.remove(dishinfo);
				}
				break;
			}
		}
		return cacheOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTemporaryOrder(String orderno) {
		CacheService cache = CacheServiceImpl.getInstance();
		return (Map<String, Object>) cache.get(KEY_PREFIX.TEMPORARY_ORDER + "_"
				+ orderno, TIME.HOUR);

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Integer getNumFromTemporaryOrder(String orderno, String dishid) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> temporaryOrder = (Map<String, Object>) cache.get(
				KEY_PREFIX.TEMPORARY_ORDER + "_" + orderno, TIME.HOUR);

		List<Map<String, Object>> dishesList = (List<Map<String, Object>>) temporaryOrder
				.get("orderdetail");
		Iterator<Map<String, Object>> itr = dishesList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> dishinfo = itr.next();
			if (dishinfo.get("dishid").equals(dishid)) {
				return (Integer) dishinfo.get("num");
			}
		}
		return null;
	}

	@Override
	public boolean validAddDishOrderNo(String fullorderno) {
		int num = jdbcTemplate
				.queryForInt("SELECT count(o.id) FROM ord_order o WHERE o.order_no = '"
						+ fullorderno
						+ "' AND (o.status = '01' OR o.status = '02') AND o.delete_flag = '0'");
		return num > 0;
	}

	@Override
	public boolean validTogetherOrderNo(String fullorderno) {
		int num = jdbcTemplate
				.queryForInt("SELECT count(o.order_no) FROM ord_number o WHERE o.order_no = '"
						+ fullorderno
						+ "' AND NOT EXISTS(SELECT id FROM ord_order WHERE order_no = o.order_no)");
		return num > 0;
	}

	@Override
	public boolean validCommentOrderNo(String fullorderno) {
		int num = jdbcTemplate
				.queryForInt("SELECT count(o.id) FROM ord_order o WHERE o.order_no = '"
						+ fullorderno
						+ "' AND (o.status = '02' OR o.status = '03') AND o.delete_flag = '0'");
		return num > 0;
	}

	@Override
	public List<Map<String, Object>> getTables(String partnercode) {
		CacheService cache = CacheServiceImpl.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jdbcTemplate", jdbcTemplate);
		params.put("partnercode", partnercode);
		List<Map<String, Object>> cacheTableList = cache.get(
				KEY_PREFIX.TABLE_LIST, "_" + partnercode, TIME.INDEFINITE,
				params, new CacheContentBuilder<List<Map<String, Object>>>() {
					@Override
					public List<Map<String, Object>> cacheContentBuilder(
							Map<String, Object> params) {

						List<Map<String, Object>> list = ((JdbcTemplate) params
								.get("jdbcTemplate"))
								.queryForList("SELECT r.id,r.table_name,r.person_count FROM rest_table r where r.rest_id = (SELECT id FROM rest_restaurant WHERE rest_code = '"
										+ params.get("partnercode")
										+ "' ) AND r.delete_flag = '0' ORDER BY r.table_name");
						return list;
					}
				});
		return cacheTableList;
	}

	@Override
	public List<Map<String, Object>> getDishesList(String orderno) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("SELECT f.id,f.food_name,f.mnem_code,f.pic,f.price,f.food_intro,(SELECT sdi.`name` FROM sys_dictionary sd,sys_dictionary_item sdi WHERE sd.id = sdi.dictionary_id AND sdi.`value`=f.unit AND sd.`key` = 'FOOD_UNIT') as unit,f.vote_num ");
		sbf.append("FROM ");
		sbf.append("(SELECT DISTINCT oi.food_id FROM ord_order o, ord_order_item oi WHERE o.id = oi.order_id AND o.order_no = '");
		sbf.append(orderno);
		sbf.append("') t,");
		sbf.append("food_food f ");
		sbf.append("WHERE ");
		sbf.append("t.food_id = f.id ");
		sbf.append("ORDER BY ");
		sbf.append("f.order_num");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sbf
				.toString());
		return list;
	}

	@Override
	public Map<String, Object> getOrderInfo(String orderno) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("SELECT o.id,o.order_no,o.vote_count,o.buffet_id FROM ord_order o WHERE o.order_no = '");
		sbf.append(orderno);
		sbf.append("'");
		Map<String, Object> orderinfo = jdbcTemplate
				.queryForMap(sbf.toString());
		return orderinfo;
	}

	@Override
	public int updateOrderVoteCount(String orderid) {
		StringBuffer sbf = new StringBuffer(500);
		sbf.append("UPDATE ord_order o set o.vote_count = o.vote_count-1 WHERE o.vote_count > 0 AND o.id = '");
		sbf.append(orderid);
		sbf.append("'");
		return jdbcTemplate.update(sbf.toString());
	}

	@Override
	public int updateMember(String mobileno) {
		StringBuffer sbf = new StringBuffer(100);
		sbf.append("UPDATE member SET last_valid_time = NOW() WHERE mobile_num = '");
		sbf.append(mobileno);
		sbf.append("'");
		return jdbcTemplate.update(sbf.toString());
	}

	@Override
	public int addMember(String mobileno) {
		StringBuffer sbf = new StringBuffer(100);
		sbf.append("INSERT INTO member(id,mobile_num,reg_time,last_valid_time) VALUES('");
		sbf.append(UUID.randomUUID());
		sbf.append("','");
		sbf.append(mobileno);
		sbf.append("',NOW(),NOW())");
		return jdbcTemplate.update(sbf.toString());
	}

	@Override
	public String getMemberIDByMobileNo(String mobileno) {
		if (mobileno == null) {
			return null;
		}
		String memberid = jdbcTemplate.queryForObject(
				"SELECT m.id FROM member m WHERE m.mobile_num = '" + mobileno
						+ "'", String.class);
		return "".equals(memberid) ? null : memberid;
	}

	@Override
	public String saveOrderInfo(String partnerid, String votecount,
			String orderno, String position, String waienum,
			String numberofpeople, String remark, String isinvoice,
			String invoicetitle, String memberid, String buffetid) {
		String id = UUID.randomUUID();
		String sql = "INSERT INTO `ord_order` VALUES ('" + id + "', '"
				+ orderno + "', '" + position + "', NOW(), "
				+ (memberid == null ? "null" : ("'" + memberid + "'"))
				+ ", '0', '0', null, null, null, '" + numberofpeople
				+ "', '01', '" + isinvoice + "', '" + invoicetitle + "', '"
				+ partnerid + "', '" + votecount + "', '01', '" + remark
				+ "', 'MEMBER', NOW(), 'MEMBER', NOW(), '0', '" + waienum
				+ "', "
				+ (buffetid == null ? "null)" : ("'" + buffetid + "')"));
		int num = jdbcTemplate.update(sql);
		if (num > 0) {
			return id;
		}
		return null;
	}

	@Override
	public String saveOrderBatch(String orderid, String remark) {
		String id = UUID.randomUUID();
		String sql = "INSERT INTO `ord_batch` VALUES ('"
				+ id
				+ "', '"
				+ orderid
				+ "', (SELECT count(id) FROM ord_batch ob WHERE ob.order_id = '"
				+ orderid + "'), 'MEMBER', NOW(), 'MEMBER', NOW(), '0','"
				+ remark + "')";
		int num = jdbcTemplate.update(sql);
		if (num > 0) {
			return id;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int saveOrderItem(String orderid, String batchid,
			Map<String, Object> temporaryorder, String restid, String buffetid,
			String memberid, String discountType, double discountNum) {
		int result = 0;
		String sql = null;
		Map<String, Object> dishinfo = null;
		String dishid = null;
		String num = null;
		String price = null;
		BigDecimal amount = null;
		List<Map<String, Object>> list = (List<Map<String, Object>>) temporaryorder
				.get("orderdetail");
		Iterator<Map<String, Object>> itr = list.iterator();
		if (discountNum == 0) {
			discountNum = 1;
		}
		boolean blReCalPrice = false;
		if (memberid != null && discountType.equals("1") && discountNum != 1) {
			blReCalPrice = true;
		}
		while (itr.hasNext()) {
			dishinfo = itr.next();
			dishid = (String) dishinfo.get("dishid");
			num = (Integer) dishinfo.get("num") + "";
			if (blReCalPrice) {
				// 重新计算会员优惠价格
				String[] priceInfo = cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper
						.getFoodPriceInfo(dishid, buffetid, restid, discountNum);
				
				price = priceInfo[4];
			} else {
				price = dishinfo.get("price").toString();
			}
			amount = new BigDecimal(num).multiply(new BigDecimal(price));
			sql = "INSERT INTO `ord_order_item` VALUES ('" + UUID.randomUUID()
					+ "', '" + orderid + "', '" + batchid + "', '" + dishid
					+ "', '" + num + "', '" + price + "', '" + amount + "', '"
					+ amount + "', 'MEMBER', NOW(), 'MEMBER', NOW(), '0',0)";
			result += jdbcTemplate.update(sql);
		}
		jdbcTemplate
				.update("UPDATE ord_order o SET o.order_amount = (SELECT SUM(i.food_amount) FROM ord_order_item i WHERE i.order_id = '"
						+ orderid
						+ "' AND i.delete_flag = '0' ),o.favor_amount = (SELECT SUM(i.favor_amount) FROM ord_order_item i WHERE i.order_id = '"
						+ orderid
						+ "' AND i.delete_flag = '0' ) WHERE o.id = '"
						+ orderid + "'");
		return result;
	}

	@Override
	public String getOrderIDByOrderNo(String orderno) {
		String orderid = jdbcTemplate.queryForObject(
				"SELECT o.id FROM ord_order o WHERE o.order_no = '" + orderno
						+ "' AND o.delete_flag = '0'", String.class);
		return "".equals(orderid) ? null : orderid;
	}

	@Override
	public String getOrderMemberIDByOrderNo(String orderno) {
		String member_id = jdbcTemplate.queryForObject(
				"SELECT o.member_id FROM ord_order o WHERE o.order_no = '"
						+ orderno + "' AND o.delete_flag = '0'", String.class);
		return "".equals(member_id) ? null : member_id;
	}

	@Override
	public int getRowNumByOrderNo(String orderno) {
		return jdbcTemplate
				.queryForInt("SELECT count(o.id) FROM ord_order o WHERE o.order_no = '"
						+ orderno + "' AND o.delete_flag = '0'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> refreshTemporaryOrder(String orderno,
			String buffetid, String partnerid) {
		Map<String, Object> cacheOrder = getTemporaryOrder(orderno);
		List<Map<String, Object>> list = (List<Map<String, Object>>) cacheOrder
				.get("orderdetail");
		if (cacheOrder != null) {
			Map<String, Object> map = null;
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				map.put("price",
						new BigDecimal(
								SpecialOfferHelper.getFoodPrice(
										(String) map.get("dishid"), buffetid,
										partnerid)));
			}
		}
		return cacheOrder;
	}

	@Override
	public int updateOrderStatusByOrderID(String orderid) {
		StringBuffer sbf = new StringBuffer(500);
		sbf.append("UPDATE ord_order o SET o.`status` = '01',o.order_state='01' WHERE o.id = '");
		sbf.append(orderid);
		sbf.append("'");
		return jdbcTemplate.update(sbf.toString());
	}

	@Override
	public Map<String, Object> getLastOrderInfo(String mobileno,
			String partnerid) {
		StringBuffer sbf = new StringBuffer(400);
		sbf.append("SELECT o.invoice_title,o.memo FROM ord_order o WHERE o.member_id = (SELECT m.id FROM member m WHERE m.mobile_num = '");
		sbf.append(mobileno);
		sbf.append("' ) AND o.rest_id = '");
		sbf.append(partnerid);
		//sbf.append("' AND o.`status` = '03' AND o.order_state = '03' ORDER BY o.create_time desc LIMIT 1");
		sbf.append("' ORDER BY o.create_time desc LIMIT 1");

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sbf
				.toString());
		return list.size() == 0 ? null : list.get(0);
	}

}
