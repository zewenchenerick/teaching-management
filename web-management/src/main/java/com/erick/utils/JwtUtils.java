package com.erick.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
public final class JwtUtils {

    /** 与测试中一致的秘钥（Base64 编码形式） */
    private static final String SECRET_KEY = "ZXJpY2s=";
    /** 12 小时的过期时长（毫秒） */
    private static final long EXPIRATION_MILLIS = 12 * 60 * 60 * 1000L;

    // 私有构造器，防止实例化
    private JwtUtils() { }

    /**
     * 根据传入的自定义 Claims 生成 JWT 令牌
     *
     * @param claims 自定义载荷信息
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                   .addClaims(claims)
                   .setExpiration(new Date(now + EXPIRATION_MILLIS))
                   .compact();
    }

    /**
     * 解析 JWT 并返回其中的 Claims
     *
     * @param token 待解析的 JWT 字符串
     * @return 解析后的 Claims 对象
     * @throws io.jsonwebtoken.JwtException 如果令牌无效或已过期
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY)
                   .parseClaimsJws(token)
                   .getBody();
    }
}