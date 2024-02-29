package com.pubsub.message.interchange.exception;

public class MessageDoesNotExistsException extends RuntimeException {

    public MessageDoesNotExistsException(String message) {
        super(message);
    }
}
