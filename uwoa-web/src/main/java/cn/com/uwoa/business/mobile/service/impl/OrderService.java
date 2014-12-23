package cn.com.uwoa.business.mobile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.business.mobile.dao.IOrderDao;
import cn.com.uwoa.business.mobile.service.IOrderService;

@Service("mobileOrderService")
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

	@Override
	public Map<String, Object> addToTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price) {
		return orderDao.addToTemporaryOrder(orderno, dishid, dishname, num,
				unit, price);
	}

	@Override
	public Map<String, Object> modifyTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price) {
		return orderDao.modifyTemporaryOrder(orderno, dishid, dishname, num,
				unit, price);
	}

	@Override
	public Map<String, Object> getTemporaryOrder(String orderno) {
		return orderDao.getTemporaryOrder(orderno);
	}

	@Override
	public Integer getNumFromTemporaryOrder(String orderno, String dishid) {
		return orderDao.getNumFromTemporaryOrder(orderno, dishid);
	}

	@Override
	public String validAddDishOrderNo(String partnercode, String orderno) {
		String fullOrderNo = this.combineOrderNo(partnercode, orderno);
		return orderDao.validAddDishOrderNo(fullOrderNo) ? fullOrderNo : null;
	}

	@Override
	public String validTogetherOrderNo(String partnercode, String orderno) {
		String fullOrderNo = this.combineOrderNo(partnercode, orderno);
		return orderDao.validTogetherOrderNo(fullOrderNo) ? fullOrderNo : null;
	}

	@Override
	public String validCommentOrderNo(String partnercode, String orderno) {
		String fullOrderNo = this.combineOrderNo(partnercode, orderno);
		return orderDao.validCommentOrderNo(fullOrderNo) ? fullOrderNo : null;
	}

	private String combineOrderNo(String partnercode, String orderno) {
		String currDate = cn.com.uwoa.system.tools.CommonTools
				.GetCurrDate("yyyyMMdd");
		return partnercode + currDate + orderno;
	}

	@Override
	public List<Map<String, Object>> getTables(String partnercode) {
		return orderDao.getTables(partnercode);
	}

	@Override
	public List<Map<String, Object>> getDishesList(String orderno) {
		return orderDao.getDishesList(orderno);
	}

	@Override
	public Map<String, Object> getOrderInfo(String orderno) {
		return orderDao.getOrderInfo(orderno);
	}

	@Override
	public boolean registerMember(String mobileno) {
		int result = orderDao.updateMember(mobileno);
		return result > 0 ? true : orderDao.addMember(mobileno) > 0;
	}

	@Override
	public boolean save(String partnerid, String votecount, String orderno,
			String position, String waienum, String numberofpeople,
			String remark, String isinvoice, String invoicetitle,
			String mobileno, boolean isadddish, String discounttype,
			double discountnum, String buffetid) {
		Map<String, Object> temporaryorder = orderDao
				.getTemporaryOrder(orderno);
		String orderid = null;
		String memberid = null;
		
		
		if(waienum!=null&&(!waienum.trim().equals(""))){
			position = "4b43074422324fa78c6656c8fc7cf833";
		}
		if (!isadddish) {
			if (orderDao.getRowNumByOrderNo(orderno) > 0) {
				return false;
			}
			memberid = orderDao.getMemberIDByMobileNo(mobileno);
			orderid = orderDao.saveOrderInfo(partnerid, votecount, orderno,
					position, waienum, numberofpeople, remark, isinvoice,
					invoicetitle, memberid, buffetid);
		} else {
			orderid = orderDao.getOrderIDByOrderNo(orderno);
			memberid = orderDao.getOrderMemberIDByOrderNo(orderno);
			orderDao.updateOrderStatusByOrderID(orderid);
		}

		String batchid = orderDao.saveOrderBatch(orderid, remark);
		int itemno = orderDao.saveOrderItem(orderid, batchid, temporaryorder,
				partnerid, "", memberid, discounttype, discountnum);
		return orderid != null && batchid != null && itemno > 0;
	}

	@Override
	public Map<String, Object> refreshTemporaryOrder(String orderno,
			String buffetid, String partnerid) {
		return orderDao.refreshTemporaryOrder(orderno, buffetid, partnerid);
	}

	@Override
	public Map<String, Object> getLastOrderInfo(String mobileno,
			String partnerid) {
		return orderDao.getLastOrderInfo(mobileno, partnerid);
	}

}
