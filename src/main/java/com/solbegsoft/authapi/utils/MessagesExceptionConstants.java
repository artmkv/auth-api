package com.solbegsoft.authapi.utils;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Exceptions Messages Constants
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessagesExceptionConstants {

    public static final String BAD_CREDENTIAL = "Token is not valid";

    public static final String TOKEN_DATE_NOT_VALID = "The date of token is not valid";
}
