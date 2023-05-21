package com.bikkadit.blogappapi.config;

public class SecurityConstants {

    public static final String INVALID_DETAILS = "Invalid Details !!!";

    public static final String BAD_CREDENTIALS = "Invalid UserName or Password !!!";

    public static final String NO_ACCESS = "Access Denied !!!";

    public static final String TOKEN_NOT_FOUND = "Unable to get JWT token";

    public static final String TOKEN_EXPIRED = "JWT token has expired";

    public static final String INVALID_JWT = "Invalid JWT Token";

    public static final String BEARER_NOT_FOUND = "JWT token doesn't begin with Bearer";

    public static final String USERNAME_CONTEXT_INVALID = "username is null or context is not null";

    public static final Integer TOKEN_VALIDITY = 5 * 60 * 60;

    public static final String TOKEN_KEY = "jwtTokenKey";


}
