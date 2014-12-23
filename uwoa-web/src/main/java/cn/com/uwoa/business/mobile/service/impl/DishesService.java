package cn.com.uwoa.business.mobile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.mobile.dao.IDishesDao;
import cn.com.uwoa.business.mobile.dao.IOrderDao;
import cn.com.uwoa.business.mobile.service.IDishesService;

@Service("mobileDishesService")
public class DishesService implements IDishesService {

	@Autowired
	private IDishesDao dishesDao;

	@Autowired
	private IOrderDao orderDao;

	@Override
	public List<Map<String, Object>> getSpecialtyList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getSpecialtyList(partnercode, partnerid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getNewList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getNewList(partnercode, partnerid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getPromotionList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getPromotionList(partnercode, partnerid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getRecommendList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getRecommendList(partnercode, partnerid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getMenuList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getMenuList(partnercode, partnerid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getCommentList(String dishesid) {
		return dishesDao.getCommentList(dishesid);
	}

	@Override
	public Map<String, Object> getDishInfo(String partnercode,
			String partnerid, String dishesid, String buffetid) {
		return dishesDao
				.getDishInfo(partnercode, partnerid, dishesid, buffetid);
	}

	@Override
	public List<Map<String, Object>> getFavouriteList(String partnerid,
			String mobileno, String buffetid) {
		return dishesDao.getFavouriteList(partnerid, mobileno, buffetid);
	}

	@Override
	public int addDishComment(String dishid, String orderid, String remark,
			String partnercode) {
		return dishesDao.addDishComment(dishid, orderid, remark, partnercode);
	}

	@Override
	public int recommend(String dishid, String orderid, String partnercode) {
		int result = orderDao.updateOrderVoteCount(orderid);
		if (result == 0) {
			return 0;
		}
		result = dishesDao.updateFoodVoteCount(dishid);
		if (result == 0) {
			return 0;
		}
		return dishesDao.addRecommend(dishid, orderid, partnercode);
	}

	@Override
	public List<Map<String, Object>> getBuffetList(String partnercode,
			String partnerid) {
		return dishesDao.getBuffetList(partnercode, partnerid);
	}

	@Override
	public List<Map<String, Object>> getBuffetItemList(String partnercode,
			String partnerid, String buffetid) {
		return dishesDao.getBuffetItemList(partnercode, partnerid, buffetid);
	}

}
