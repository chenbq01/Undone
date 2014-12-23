package cn.com.uwoa.business.mobile.dao;

import java.util.List;
import java.util.Map;

public interface IOrderDao {

	public Map<String, Object> addToTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price);

	public Map<String, Object> modifyTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price);

	public Map<String, Object> getTemporaryOrder(String orderno);

	public Integer getNumFromTemporaryOrder(String orderno, String dishid);

	public boolean validAddDishOrderNo(String fullorderno);

	public boolean validTogetherOrderNo(String fullorderno);

	public boolean validCommentOrderNo(String fullorderno);

	public List<Map<String, Object>> getTables(String partnercode);

	public List<Map<String, Object>> getDishesList(String orderno);

	public Map<String, Object> getOrderInfo(String orderno);

	public int updateOrderVoteCount(String orderid);

	public int updateMember(String mobileno);

	public int addMember(String mobileno);

	public String getMemberIDByMobileNo(String mobileno);

	public String saveOrderInfo(String partnerid, String votecount,
			String orderno, String position, String waienum,
			String numberofpeople, String remark, String isinvoice,
			String invoicetitle, String memberid, String buffetid);

	public String saveOrderBatch(String orderid, String remark);

	public int saveOrderItem(String orderid, String batchid,
			Map<String, Object> temporaryorder, String restid, String buffetid,
			String memberid, String discountType, double discountNum);

	public String getOrderIDByOrderNo(String orderno);

	public String getOrderMemberIDByOrderNo(String orderno);

	public int getRowNumByOrderNo(String orderno);

	public Map<String, Object> refreshTemporaryOrder(String orderno,
			String buffetid, String partnerid);

	public int updateOrderStatusByOrderID(String orderid);

	public Map<String, Object> getLastOrderInfo(String mobileno,
			String partnerid);

}
