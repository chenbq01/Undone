package org.season.autumn.helper;

import java.io.UnsupportedEncodingException;

import org.season.autumn.exception.BaseException;

public class FileHelper {

	public static String getExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return "";
	}

	public static String encodingFileName(String fileName) {
		String returnFileName = "";
		try {
			returnFileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("文件名转码时出错", e);
		}
		return returnFileName;
	}

}
