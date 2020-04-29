package com.graduation.education.user.service.course;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;


/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/26 20:01
 */
public class SuperClassUtil {

    public static String encrypt(String string) {
        String result = null;
        try {
            result = aes(URLEncoder.encode(string, "utf-8"), md5("friday_syllabus"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String aes(String context, String key) {
        String result = "";
        Key secretKeySpec;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            secretKeySpec = new SecretKeySpec(messageDigest.digest(key.getBytes("utf-8")), "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            result = byteToStr(instance.doFinal(context.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String byteToStr(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString.toUpperCase());
        }
        return stringBuilder.toString();
    }

    private static String md5(String str) {
        int i = 0;
        String str2 = null;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            while (i < 16) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            str2 = new String(cArr2).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }
}
