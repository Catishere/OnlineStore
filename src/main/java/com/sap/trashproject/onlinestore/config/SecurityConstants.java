package com.sap.trashproject.onlinestore.config;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/users/authenticate";

    public static final String JWT_SECRET = "E)H+MbQeThWmZq4t7w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "online-store-api";
    public static final String TOKEN_AUDIENCE = "online-store-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}