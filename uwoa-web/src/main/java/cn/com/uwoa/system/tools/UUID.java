package cn.com.uwoa.system.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UUID {
	public static String randomUUID(){
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static String Md5 (String plainText) 
	{ 
		try 
		{ 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 
			int i; 
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++)
			{ 
				i = b[offset]; 
				if(i<0) 
					i+= 256; 
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 
			return buf.toString();
			
			//32位的加密 System.out.println("result: " + buf.toString().substring(8,24));//16位的加密 
		} 
		catch (NoSuchAlgorithmException e) 
		{ 
			return null;
		}
	}
}
