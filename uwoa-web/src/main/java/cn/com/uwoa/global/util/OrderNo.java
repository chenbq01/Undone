package cn.com.uwoa.global.util;

import java.util.Random;

import cn.com.uwoa.global.web.controller.*;
import cn.com.uwoa.system.tools.SpringContextHolder;

public class OrderNo {

	private static char[] resources = {// 修改为全部为数字
	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public synchronized static String generate(String restcode) {

		//
		
		boolean exist = true;
		String orderNo = "";
		String randomCode = "";
		GlobalController g = SpringContextHolder.getBean("globalController");
		String currDate = cn.com.uwoa.system.tools.CommonTools
				.GetCurrDate("yyyyMMdd");
		while (exist) {
			randomCode = getRandomCode();
			if (randomCode.equals("000000"))
				randomCode = "000001";
			orderNo = restcode + currDate + getRandomCode();

			// 判断订单号没有被使用
			exist = g.ExistOrder(orderNo);
		}
		// 插入使用的订单号
		g.AddOrderNu(orderNo, currDate);

		 return orderNo;
		
		//return "20130711000001";
	}
	public synchronized static String generatebyRest_id(String rest_id) {

		//
		GlobalController g = SpringContextHolder.getBean("globalController");
		String rest_code = g.getRestCode(rest_id);
		return generate(rest_code);
		
	}

	private synchronized static String getRandomCode() {
		StringBuffer sRand = new StringBuffer();
		// 生成随机类
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			char rand = resources[random.nextInt(resources.length)];// String.valueOf(random.nextInt(10));
			sRand.append(rand);

		}
		return sRand.toString();
	}

}
