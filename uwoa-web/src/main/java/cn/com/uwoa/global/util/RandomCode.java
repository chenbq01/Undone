package cn.com.uwoa.global.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomCode {

	public static String generate() {
		return RandomStringUtils.randomNumeric(4);
	}

	public static String generate(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

}
