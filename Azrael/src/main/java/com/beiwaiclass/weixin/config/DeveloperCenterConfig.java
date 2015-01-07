package com.beiwaiclass.weixin.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class DeveloperCenterConfig {

	private static Logger logger = LoggerFactory
			.getLogger(DeveloperCenterConfig.class);

	private static String token = null;

	private static String request_url = null;

	public static String getToken() {
		if (token == null) {
			Resource resource = new ClassPathResource(
					"/META-INF/spring/weixin.properties");
			try {
				Properties props = PropertiesLoaderUtils
						.loadProperties(resource);
				token = props.getProperty("token");
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error("无法从配置文件中获取服务器配置的Token", e);
				}
			}
		}
		return token;
	}

	public static String getRequestUrl() {
		if (request_url == null) {
			Resource resource = new ClassPathResource(
					"/META-INF/spring/weixin.properties");
			try {
				Properties props = PropertiesLoaderUtils
						.loadProperties(resource);
				request_url = props.getProperty("access_token_request_url");
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error("无法从配置文件中读取访问AccessToken的请求地址", e);
				}
			}
		}
		return request_url;
	}

	public static void main(String[] args) {
		System.out.println(getToken());
		System.out.println(getRequestUrl());
	}

}
