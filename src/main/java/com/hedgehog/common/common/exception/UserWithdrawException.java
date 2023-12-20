package com.hedgehog.common.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UserWithdrawException extends AuthenticationException {
    public UserWithdrawException(String message) {
        super(message);
    }
}
