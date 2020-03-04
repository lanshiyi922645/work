package com.xiaoshamo.work.commons.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util implements Serializable {
    private static final long serialVersionUID = 10012828388338L;
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static MessageDigest md = null;
    private static boolean inited = false;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
            inited = true;
        } catch (NoSuchAlgorithmException var1) {
            inited = false;
        }

    }

    public MD5Util() {
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String getMD5Encode(String origin) throws Exception {
        if (origin == null) {
            return null;
        } else {
            origin = origin + "b&J%OI1iuo*88oZd";
            if (!inited) {
                throw new Exception("MD5 算法实例初始化错误！");
            } else {
                byte[] temp;
                synchronized (md) {
                    temp = md.digest(origin.getBytes());
                }

                return byteArrayToHexString(temp);
            }
        }
    }

    public static String getMD5(String origin) throws Exception {
        if (origin == null) {
            return null;
        } else if (!inited) {
            throw new Exception("MD5 算法实例初始化错误！");
        } else {
            byte[] temp;
            synchronized (md) {
                temp = md.digest(origin.getBytes());
            }

            return byteArrayToHexString(temp);
        }
    }

    public static String getMD5(String origin, String charset) throws Exception {
        if (origin == null) {
            return null;
        } else if (!inited) {
            throw new Exception("MD5 算法实例初始化错误！");
        } else {
            byte[] temp;
            synchronized (md) {
                temp = md.digest(origin.getBytes(charset));
            }

            return byteArrayToHexString(temp);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getMD5Encode("123456"));
    }
}