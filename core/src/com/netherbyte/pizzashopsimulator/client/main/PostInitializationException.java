package com.netherbyte.pizzashopsimulator.client.main;

public class PostInitializationException extends RuntimeException {
    public PostInitializationException() {
        super();
    }

    public PostInitializationException(String message) {
        super(message);
    }

    public PostInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostInitializationException(Throwable cause) {
        super(cause);
    }

    protected PostInitializationException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
