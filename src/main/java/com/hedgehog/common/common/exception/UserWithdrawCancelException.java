package com.hedgehog.common.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UserWithdrawCancelException extends AuthenticationException {
    public UserWithdrawCancelException(String message) {
        super(message);
    }
}
