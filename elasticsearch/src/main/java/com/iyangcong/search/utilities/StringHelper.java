package com.iyangcong.search.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

}
