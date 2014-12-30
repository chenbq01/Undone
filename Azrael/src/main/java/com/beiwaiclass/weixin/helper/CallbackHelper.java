package com.beiwaiclass.weixin.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beiwaiclass.weixin.config.DeveloperCenterConfig;

public class CallbackHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(CallbackHelper.class);

	public static boolean validCallbackInfo(String signature, String timestamp,
			String nonce) {

		String paramstring = BuildSortedParamString(timestamp, nonce);
		if (logger.isDebugEnabled()) {
			logger.debug(EncryptHelper.SHA1(paramstring));
		}
		if (signature.equals(EncryptHelper.SHA1(paramstring))) {
			return true;
		}
		return false;
	}

	private static String BuildSortedParamString(String timestamp, String nonce) {
		final StringBuilder builder = new StringBuilder();
		final List<String> sortedStrList = new ArrayList<String>();
		sortedStrList.add(DeveloperCenterConfig.getToken());
		sortedStrList.add(timestamp);
		sortedStrList.add(nonce);
		Collections.sort(sortedStrList);

		for (final String str : sortedStrList) {
			builder.append(str);
		}
		return builder.toString();
	}

}
