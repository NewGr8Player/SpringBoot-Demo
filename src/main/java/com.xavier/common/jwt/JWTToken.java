package com.xavier.common.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <pre>
 * JWTToken 对象
 * 实现{@code org.apache.shiro.authc.AuthenticationToken}
 * </pre>
 *
 * @author NewGr8Player
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
