package cn.toy.www.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptUtils {

    // 密钥
    private static final String KEY = "123456a?";

    public static void main(String[] args) {
        String ciphertext1 = encrypt("xmxc1234"); // Wu11fsC0gpgSET5aU8GXUA==
        String ciphertext2 = encrypt("xmxc1234"); // ESXlHsVk2YM7mGcHy2ccGg==
        System.out.println(ciphertext1);
        System.out.println(ciphertext2);

        String text1 = decrypt(ciphertext1);
        String text2 = decrypt(ciphertext2);
        System.out.println(text1);               // abcdefg
        System.out.println(text2);               // abcdefg
    }

    /**
     * 加密
     * @param text 明文
     * @return     密文
     */
    public static String encrypt(String text) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.encrypt(text);
    }

    /**
     * 解密
     * @param ciphertext 密文
     * @return           明文
     */
    public static String decrypt(String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.decrypt(ciphertext);
    }

}
