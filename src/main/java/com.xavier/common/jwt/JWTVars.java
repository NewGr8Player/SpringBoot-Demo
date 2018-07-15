package com.xavier.common.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTVars {

    /**
     * Tocken 超时时间
     */
    public static Long EXPIRE_TIME;

    /**
     * 秘钥
     */
    public static String SECRET;

    /**
     * Http header中存储Token的字段名
     */
    public static String HEADER_NAME;

    @Value("${jwt.header_name}")
    public void setHeaderName(String headerNameame) {
        HEADER_NAME = headerNameame;
    }

    @Value("${jwt.expire_time}")
    public void setExpireTime(Long expireTime) {
        EXPIRE_TIME = expireTime;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        SECRET = secret;
    }
}
