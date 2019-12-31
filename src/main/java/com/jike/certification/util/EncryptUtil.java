package com.jike.certification.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * 内容加密编码工具类
 * @author wentong
 */
@Slf4j
public class EncryptUtil {

    /**
     * 身份证长度
     */
    private static final int ID_CARD_LEN = 18;

    private static final String AES_KEY = "MjsII$@%R82BwDFK8k342n5cx";

    private static String encryptMobile(String mobile) {
        if (Strings.isNullOrEmpty(mobile)) {
            return StringUtils.EMPTY;
        }

        int len = mobile.length();
        return mobile.substring(0, len - 7) + "****" + mobile.substring(len - 3);
    }

    /**
     * 加密身份证号:11-16位
     *
     * @param idCard 原始身份证号
     * @return 加密后的身份证号
     */
    private static String encryptIdCard(String idCard) {
        if (Strings.isNullOrEmpty(idCard) || idCard.length() != ID_CARD_LEN) {
            return idCard;
        }
        return idCard.substring(0, 10) + "******" + idCard.substring(16);
    }

    public static String aesEncrypt(String str, String key) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(key)) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return encryptBASE64(bytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("aes encrypt error", e);
        }
    }

    public static String aesDecrypt(String str, String key) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(key)) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = decryptBASE64(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            throw new IllegalArgumentException("aes decrypt error", e);
        }
    }

    public static String aesEncryptUrlSafe(String str, String key) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(key)) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("aes encrypt error", e);
        }
    }

    public static String aesDecryptUrlSafe(String str, String key) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(key)) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = Base64.getUrlDecoder().decode(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            throw new IllegalArgumentException("aes decrypt error", e);
        }
    }

    public static String aesEncrypt(String str) {
        return EncryptUtil.aesEncrypt(str, EncryptUtil.getKey());
    }

    public static String aesDecrypt(String str) {

        if (Strings.isNullOrEmpty(str)) {
            return "";
        }

        str = str.replace("XXX_", "");

        return EncryptUtil.aesDecrypt(str, EncryptUtil.getKey());
    }

    public static String aesEncryptUrlSafe(String str) {
        return EncryptUtil.aesEncryptUrlSafe(str, getKey());
    }

    public static String aesDecryptUrlSafe(String str) {

        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        return EncryptUtil.aesDecryptUrlSafe(str, getKey());
    }

    public static String getKey() {
        return DigestUtils.md5Hex(EncryptUtil.AES_KEY).substring(0, 16);
    }

    /**
     * BASE64Encoder 加密
     *
     * @param data 要加密的数据
     * @return 加密后的字符串
     */
    public static String encryptBASE64(byte[] data) {
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Encoder
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(data);
        return encode;
    }

    /**
     * BASE64Decoder 解密
     *
     * @param data 要解密的字符串
     * @return 解密后的byte[]
     * @throws Exception
     */
    public static byte[] decryptBASE64(String data) throws Exception {
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Decoder
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        return buffer;
    }

}
