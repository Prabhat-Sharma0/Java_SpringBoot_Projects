package com.restApi.BasicRestApplication.exception;

import java.io.Serial;

public class UserContextException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserContextException(String message) {
        super(message);
    }

}
