package cn.wushi.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String FIXED_KEY = "1145141145141145"; // 固定密钥

    private Cipher decryptCipher;
    private Cipher encryptCipher;

    private AESUtil() throws Exception {
        SecretKey key = getSecretKey(FIXED_KEY);
        decryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
        encryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
    }

    public static AESUtil getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String decrypt(String content) throws Exception {
        byte[] bytes = Base64.decodeBase64(content);
        byte[] result = decryptCipher.doFinal(bytes);
        return new String(result, StandardCharsets.UTF_8);
    }

    public String encrypt(String content) throws Exception {
        byte[] result = encryptCipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(result);
    }

    private SecretKey getSecretKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, KEY_ALGORITHM);
    }

    private static class SingletonHelper {
        private static final AESUtil INSTANCE;

        static {
            try {
                INSTANCE = new AESUtil();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create AESUtil instance", e);
            }
        }
    }
}
