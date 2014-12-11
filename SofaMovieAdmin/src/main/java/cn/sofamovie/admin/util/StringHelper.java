package cn.sofamovie.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(StringHelper.class);

	public static String urlStringDecoder2UTF8(String str) {
		String decodedString = null;
		try {
			if (str != null) {
				decodedString = java.net.URLDecoder.decode(str, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("字符串[" + str + "]转码为UTF-8错误！", e);
		}
		return decodedString;
	}

	public static String urlStringEncoder2UTF8(String str) {
		String encodedString = null;
		try {
			if (str != null) {
				encodedString = URLEncoder.encode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("字符串[" + str + "]转码为UTF-8错误！", e);
		}
		return encodedString;

	}

	/**
	 * 对字符串进行加密处理
	 * 
	 * @param str
	 * @return 加密后的字符串
	 */
	public static String encryptStringByMD5(String str) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		byte[] btInput = str.getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst;
		try {
			mdInst = MessageDigest.getInstance("MD5");

			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char chrs[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				chrs[k++] = hexDigits[byte0 >>> 4 & 0xf];
				chrs[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(chrs);
		} catch (NoSuchAlgorithmException e) {
			logger.error("无法获取MD5加密算法", e);
			return null;
		}

	}

	public static void main(String[] args) {
		System.out.println(encryptStringByMD5("123456"));
	}

}
