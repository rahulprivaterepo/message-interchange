package com.pubsub.message.interchange.exception;

public class UnauthorizedToPublishException extends RuntimeException {

    public UnauthorizedToPublishException(String message) {
        super(message);
    }
}
