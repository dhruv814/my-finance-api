package com.finance.authentication.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKey;

public class SpringSecurityContext {

    public static final SecretKey JWT_KEY = getJwtKey();
    public static final String JWT_KEY_STRING = getJwtKeyString();
    public static final String JWT_HEADER = "Authorization";
    public static final int JWT_EXPIRATION = 3000;
    public static final String[] PUBLIC_API = {
        "/v3/api-docs/**",
        "/api/v1/auth/**",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/register/**",
        "/login/**",
        "/login"
    };

    private static SecretKey getJwtKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    private static String getJwtKeyString() {
        // Generate a secure random key
        byte[] keyBytes = new byte[32]; // 256 bits
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

}
