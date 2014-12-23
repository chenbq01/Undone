package cn.com.uwoa.business.mobile.dao;

import java.util.List;
import java.util.Map;

public interface IDishesDao {

	public List<Map<String, Object>> getSpecialtyList(String partnercode,
			String partnerid, String buffetid);

	public List<Map<String, Object>> getNewList(String partnercode,
			String partnerid, String buffetid);

	public List<Map<String, Object>> getPromotionList(String partnercode,
			String partnerid, String buffetid);

	public List<Map<String, Object>> getRecommendList(String partnercode,
			String partnerid, String buffetid);

	public List<Map<String, Object>> getMenuList(String partnercode,
			String partnerid, String buffetid);

	public List<Map<String, Object>> getCommentList(String dishesid);

	public Map<String, Object> getDishInfo(String partnercode,
			String partnerid, String dishesid, String buffetid);

	public List<Map<String, Object>> getFavouriteList(String partnerid,
			String mobileno, String buffetid);

	public int addDishComment(String dishid, String orderid, String remark,
			String partnercode);

	public int addRecommend(String dishid, String orderid, String partnercode);

	public List<Map<String, Object>> getBuffetList(String partnercode,
			String partnerid);

	public List<Map<String, Object>> getBuffetItemList(String partnercode,
			String partnerid, String buffetid);
	
	public int updateFoodVoteCount(String dishid);

}
