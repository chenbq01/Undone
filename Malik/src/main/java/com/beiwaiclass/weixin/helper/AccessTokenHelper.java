package com.beiwaiclass.weixin.helper;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(AccessTokenHelper.class);

	private static String ACCESS_TOKEN = null;

	private static String APP_ID = "wxd032fd57ca9a9a79";

	private static String APP_SECRET = "587b241d21edec37b3badff8f179b0b6";

	public static String returnAccessToken() {
		if (ACCESS_TOKEN == null) {
			requestAccessToken();
		}
		return ACCESS_TOKEN;
	}

	public static void requestAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ APP_ID + "&secret=" + APP_SECRET;
		String responseBody = null;
		String access_token = null;
		Long expires_in = 0l;

		responseBody = HttpConnectionHelper.responseBody(url, 10000);
		JSONObject obj = JSONObject.fromObject(responseBody);
		access_token = obj.getString("access_token");
		expires_in = obj.getLong("expires_in");
		if (logger.isDebugEnabled()) {
			logger.debug("access_token:" + access_token);
			logger.debug("expires_in:" + expires_in);
		}
		ACCESS_TOKEN = access_token;
	}

}
