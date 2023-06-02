package com.ustb.evaluation.mod01common.utils.security;

import com.ustb.evaluation.mod01common.setting.GlobalSettings;
import com.ustb.evaluation.mod01common.domain.http.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String JWT_PAYLOAD_USER_KEY = "user";

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtil.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }


    /**
     * 私钥加密token(使用默认的token有效时间)
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtil.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusMinutes(Integer.parseInt(GlobalSettings.TOKEN_KEEPLIVE.getValue())).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }


//    /**
//     * 私钥加密token
//     *
//     * @param userInfo   载荷中的数据
//     * @param privateKey 私钥
//     * @param expire     过期时间，单位秒
//     * @return JWT
//     */
//    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey, int expire) {
//        return Jwts.builder()
//                .claim(JWT_PAYLOAD_USER_KEY, JsonUtil.toString(userInfo))
//                .setId(createJTI())
//                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
//                .signWith(privateKey, SignatureAlgorithm.RS256)
//                .compact();
//    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtil.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 判断token是否过期，过期返回true
     */
    public static Boolean tokenIsExpired(Date tokenExpired) {
        if (new Date().before(tokenExpired)) return false;
        return true;
    }

}


