package com.beiwaiclass.weixin.helper;

import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class AccessTokenHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(AccessTokenHelper.class);

	private static String ACCESS_TOKEN = null;

	private static String app_id = null;

	private static String app_secret = null;

	public static String returnAccessToken() {
		if (ACCESS_TOKEN == null) {
			requestAccessToken();
		}
		return ACCESS_TOKEN;
	}

	private static String getAppID() {
		if (app_id == null) {
			Resource resource = new ClassPathResource(
					"/META-INF/weixin.properties");
			try {
				Properties props = PropertiesLoaderUtils
						.loadProperties(resource);
				app_id = props.getProperty("app_id");
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error("无法从配置文件中获取服务器配置的APP_ID", e);
				}
			}
		}
		return app_id;
	}

	private static String getAppSecret() {
		if (app_secret == null) {
			Resource resource = new ClassPathResource(
					"/META-INF/weixin.properties");
			try {
				Properties props = PropertiesLoaderUtils
						.loadProperties(resource);
				app_secret = props.getProperty("app_secret");
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error("无法从配置文件中获取服务器配置的APP_SECRET", e);
				}
			}
		}
		return app_secret;
	}

	public static void requestAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ getAppID() + "&secret=" + getAppSecret();
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
