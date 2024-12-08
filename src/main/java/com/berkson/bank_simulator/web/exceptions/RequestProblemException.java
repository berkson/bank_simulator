package com.berkson.bank_simulator.web.exceptions;

public class RequestProblemException extends Exception {
    public RequestProblemException() {
    }

    public RequestProblemException(String message) {
        super(message);
    }

    public RequestProblemException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestProblemException(Throwable cause) {
        super(cause);
    }

    protected RequestProblemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
