package cn.wushi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtil {
    private static final String SECRET_KEY = "114514"; // 固定的 JWT 签名密钥
    private final Map<String, Object> header = new HashMap<>();
    private AESUtil aesUtil;

    public JWTUtil() {
        try {
            header.put("alg", "HS256");
            header.put("typ", "JWT");
            aesUtil = AESUtil.getInstance(); // 获取AESUtil的单例实例
        } catch (Exception e) {
            // 处理AESUtil初始化异常
            e.printStackTrace();
        }
    }

    /**
     * 生成 token
     *
     * @param map
     * @return token
     */
    public String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 300);
        JWTCreator.Builder builder = JWT.create();
        map.put("id", UUID.randomUUID().toString());
        map.forEach(builder::withClaim);
        String token = builder
                .withHeader(header)
                .withIssuedAt(new Date())
                .withExpiresAt(instance.getTime()) // 设置过期时间
                .sign(Algorithm.HMAC256(SECRET_KEY));
        try {
            return aesUtil.encrypt(token); // 使用AES加密Token
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证 token
     *
     * @param encryptedToken
     * @return userId
     */
    public String verify(String encryptedToken) {
        try {
            String token = aesUtil.decrypt(encryptedToken); // 使用AES解密Token
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token); // 使用相同的秘钥解码JWT
            return jwt.getClaim("userId").asString(); // 从JWT的负载中获取userId声明
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
