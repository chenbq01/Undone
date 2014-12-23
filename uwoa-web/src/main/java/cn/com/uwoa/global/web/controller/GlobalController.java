package cn.com.uwoa.global.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.uwoa.global.service.IGlobalService;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;



@Controller
public class GlobalController  extends FrameController  {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);
	
	/**
	 * 服务对象
	 */
	@Autowired
	private IGlobalService globalService;
	
	public boolean ExistOrder(String  orderNum) {
		String sql = "select count(order_no) from ord_number where order_no='" + orderNum +"'";
		String count = getService().getSingleInfo(sql);
		if(count.trim().equals("0")){
			return false;
		}
		return true;
	}
	
	public String getRestCode(String rest_id) {
		String sql = "select rest_code from rest_restaurant where id='" + rest_id +"'";
		String rest_code = getService().getSingleInfo(sql);
		
		return rest_code;
	}
	
	public String getRestName(String rest_id) {
		String sql = "select rest_name from rest_restaurant where id='" + rest_id +"'";
		String rest_code = getService().getSingleInfo(sql);
		
		return rest_code;
	}
	public int AddOrderNu(String orderNum,String orderDate){
		String sql = "insert into ord_number(order_no,order_date) values('" + orderNum +"','" + orderDate + "');";
		return  getService().executeNonQuery(sql);
		
	}
	
	@Override
	protected IFrameService getService() {
		return (IFrameService)globalService;
	}

	@Override
	public String getPage() {
		// TODO Auto-generated method stub
		return null;
	}
}
