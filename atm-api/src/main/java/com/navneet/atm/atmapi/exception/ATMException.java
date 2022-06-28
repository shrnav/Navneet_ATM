package com.navneet.atm.atmapi.exception;

public class ATMException extends Exception {

    private static final long serialVersionUID = 1L;

    public ATMException() {
        super();
    }

    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }
}
