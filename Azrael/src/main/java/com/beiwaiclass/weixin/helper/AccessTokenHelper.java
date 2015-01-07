package com.beiwaiclass.weixin.helper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beiwaiclass.weixin.config.DeveloperCenterConfig;

public class AccessTokenHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(AccessTokenHelper.class);

	public static String getAccessToken() {
		String accesstoken = null;
		try {
			accesstoken = Jsoup.connect(DeveloperCenterConfig.getRequestUrl())
					.get().text();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("无法从中控服务器获取AccessToken", e);
			}
		}
		return accesstoken;
	}

}
