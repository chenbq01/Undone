package com.beiwaiclass.weixin.helper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpConnectionHelper.class);

	public static void trustEveryone() {

		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		SSLContext context = null;
		try {
			context = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			if (logger.isErrorEnabled()) {
				logger.error("初始化SSLContext时出错", e);
			}
		}
		try {
			context.init(null, new X509TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());
		} catch (KeyManagementException e) {
			if (logger.isErrorEnabled()) {
				logger.error("初始化SSLContext时出错", e);
			}
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(context
				.getSocketFactory());

	}

	public static String responseBody(String url, int timeout) {
		trustEveryone();
		Connection connection = HttpConnection.connect(url).timeout(timeout)
				.header("Accept-Encoding", "gzip,deflate,sdch")
				.header("Connection", "close");

		String responseBody = null;
		try {
			responseBody = connection.ignoreContentType(true).execute().body();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("获取["+url+"]的响应体内容时出错(超时时间为"+timeout+")", e);
			}
		}
		return responseBody;

	}

}
