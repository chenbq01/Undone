package com.beiwaiclass.weixin.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Daly.Osborn on 14-2-12.
 */
public final class EncryptHelper {

    private EncryptHelper(){
        throw new UnsupportedOperationException("");
    }
    /**
     * sha1加密
     */
    public static String SHA1(final String src){
        return Encrypt(src, "SHA-1");
    }

    public static String MD5(final String src){
        return Encrypt(src, "MD5");
    }

    /**
     * 加密方法
     * @param strSrc 被加密的内容
     * @param encName 加密的算法名，默认为SHA-1，也可以用MD5等等
     */
    private static String Encrypt(String strSrc,String encName) {
        MessageDigest messageDigest = null;
        String strDes = null;

        byte[] bytes = strSrc.getBytes();
        try {
            if (encName==null || encName.equals("")) {
                encName="SHA-1";
            }
            messageDigest = MessageDigest.getInstance(encName);
            messageDigest.update(bytes);
            strDes = bytes2Hex(messageDigest.digest());  //to HexString
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    /**
     * 转换数字的进制
     */
    private static String bytes2Hex(byte[] bytes) {
        String des = "";
        String tmp = null;
        for(final byte b : bytes) {
            tmp = (Integer.toHexString(b & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
