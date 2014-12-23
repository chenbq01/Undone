package cn.com.uwoa.business.mobile.service;

import java.util.List;
import java.util.Map;

public interface IOrderService {

	public Map<String, Object> addToTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price);

	public Map<String, Object> modifyTemporaryOrder(String orderno,
			String dishid, String dishname, String num, String unit,
			String price);

	public Map<String, Object> getTemporaryOrder(String orderno);

	public Integer getNumFromTemporaryOrder(String orderno, String dishid);

	public String validAddDishOrderNo(String partnercode, String orderno);

	public String validTogetherOrderNo(String partnercode, String orderno);

	public String validCommentOrderNo(String partnercode, String orderno);

	public List<Map<String, Object>> getTables(String partnercode);

	public List<Map<String, Object>> getDishesList(String orderno);

	public Map<String, Object> getOrderInfo(String orderno);

	public boolean registerMember(String mobileno);

	public boolean save(String partnerid, String votecount, String orderno,
			String position, String waienum, String numberofpeople,
			String remark, String isinvoice, String invoicetitle,
			String mobileno, boolean isadddish, String discounttype,
			double discountnum, String buffetid);

	public Map<String, Object> refreshTemporaryOrder(String orderno,
			String buffetid, String partnerid);

	public Map<String, Object> getLastOrderInfo(String mobileno,
			String partnerid);

}
