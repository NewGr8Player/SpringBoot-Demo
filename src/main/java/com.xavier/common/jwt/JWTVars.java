package com.xavier.common.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTVars {
    public static Long EXPIRE_TIME;/* Tocken 超时时间 */

    public static String SECRET;/* 秘钥 */

    public static String HEADER_NAME;/* Http header中存储Token的字段名 */

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
