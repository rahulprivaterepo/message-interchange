package com.pubsub.message.interchange.exception;

public class NoNewMessageInTheTopicException extends RuntimeException {

    public NoNewMessageInTheTopicException(String message) {
        super(message);
    }
}
