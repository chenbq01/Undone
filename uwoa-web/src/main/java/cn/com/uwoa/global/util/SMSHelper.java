package cn.com.uwoa.global.util;

import java.rmi.RemoteException;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.uwoa.global.web.controller.GlobalController;
import cn.com.uwoa.system.tools.SpringContextHolder;
import cn.com.uwoa.system.tools.UUID;

public class SMSHelper {

	private static SMSHelper instance = new SMSHelper();

	public final static long DEFAULT_SMSID = 11111;

	public final static String DEFAULT_ADDSERIAL = "";

	public final static int DEFAULT_SMSPRIORITY = 5;

	private SMSHelper() {
	}

	public static SMSHelper getInstance() {
		return instance;
	}

	public static int simplesend(String partnercode, String mobileno,
			String smsContent) throws RemoteException {

		int result = SingletonClient.getClient().sendSMS(
				new String[] { mobileno }, smsContent,"",5);
		JdbcTemplate jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");
		jdbcTemplate
				.update("INSERT INTO sms_log(id,rest_id,mobile_num,send_content,send_time,send_type) VALUES('"
						+ UUID.randomUUID()
						+ "',(SELECT r.id FROM rest_restaurant r WHERE r.rest_code = '"
						+ partnercode
						+ "' AND r.delete_flag='0'),'"
						+ mobileno
						+ "','" + smsContent + "',NOW(),'0')");
		return result;
	}
	
	public static int adsimplesend(String partnercode, String mobileno,
			String smsContent) throws RemoteException {

		GlobalController g = SpringContextHolder.getBean("globalController");
		String sRestName = g.getRestName(partnercode);
		smsContent = smsContent +"，此信息来自【" + sRestName + "】。";
		int result = SingletonClient.getAdClient().sendSMS(
				new String[] { mobileno }, smsContent,"",5);
		JdbcTemplate jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");
		jdbcTemplate
				.update("INSERT INTO sms_log(id,rest_id,mobile_num,send_content,send_time,send_type) VALUES('"
						+ UUID.randomUUID()
						+ "','" + partnercode + "','"
						+ mobileno
						+ "','" + smsContent + "',NOW(),'1')");
		return result;
	}

}
