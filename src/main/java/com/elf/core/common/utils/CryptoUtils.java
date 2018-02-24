package com.elf.core.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * <br>Title: CryptoUtils
 * <br>Description: 加密解密
 * <br>Author:李一鸣(li-yiming@neusoft.com)
 * <br>Date:2013-6-18
 */
public class CryptoUtils {

    private static final String PASSWORD_CRYPT_KEY = "__ELF@Lym5236__";

    /**
     * <br>Description: 二行制转字符串
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * <br>Description: hex2byte
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数！");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * <br>Description: 加密
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月29日
     * @param paramString1
     * @param paramString2
     * @return
     */
    public static String encrypt(String paramString1, String paramString2) throws Exception {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString2);
        localMessageDigest.reset();
        byte[] arrayOfByte1 = paramString1.getBytes();
        byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
        return byte2hex(arrayOfByte2);
    }

    /**
     * <br>Description: DES加密
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static byte[] DESEncrypt(byte[] src, byte[] key) throws Exception {
        //DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        //获取数据并加密正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * <br>Description: DES解密
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] DESDecrypt(byte[] src, byte[] key) throws Exception {
        //DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        //创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        //Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        //获取数据并解密正式执行解密操作
        return cipher.doFinal(src);
    }

    /**
     * <br>Description: DES密码解密
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param data
     * @return
     */
    public final static String DESDecrypt(String data) {
        try {
            return new String(DESDecrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <br>Description: DES密码加密
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     * @param password
     * @return
     */
    public final static String DESEncrypt(String password) {
        try {
            return byte2hex(DESEncrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <br>Description: MD5密码加密
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月29日
     * @param password
     * @return
     */
    public final static String MD5Encrypt(String password) {
        try {
            return encrypt(password, "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <br>Description: SHA1密码加密
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月29日
     * @param password
     * @return
     */
    public final static String SHA1Encrypt(String password) {
        try {
            return encrypt(password, "SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "Lym@102526";
        String str1 = CryptoUtils.DESEncrypt(str);
        System.out.println("DES加密：" + str1);
        System.out.println("DES解密：" + CryptoUtils.DESDecrypt(str1));
        System.out.println("MD5加密：" + CryptoUtils.MD5Encrypt(str));
        System.out.println("SHA1加密：" + CryptoUtils.SHA1Encrypt(str));
    }
}
