package com.navneet.atm.atmapi.exception;

public class AccountException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
