package cn.p2nn.meteor.utils;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * YAML文件加密与解密工具类
 *
 * @author huangjiayao1993
 */
public class JasyptUtil {

    private static final String PASSWORD = "tNFwPPxxv6#+&SlB";

    private static PooledPBEStringEncryptor loadEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PASSWORD);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static String encrypt(String plainText) {
        return loadEncryptor().encrypt(plainText);
    }

    public static String decrypt(String encryptedText) {
        return loadEncryptor().decrypt(encryptedText);
    }

    public static void main(String[] args) {
        // 待加密明文
        String plainText = "R7OAXNb/a9+LUTpbPHHevA==";
        // 加密
        String encryptWithSHA512Str = encrypt(plainText);
        System.out.println("加密后密文：" + encryptWithSHA512Str);
        // 解密
        String decryptWithSHA512Str = decrypt(encryptWithSHA512Str);
        System.out.println("解密后明文:" + decryptWithSHA512Str);
    }

}
