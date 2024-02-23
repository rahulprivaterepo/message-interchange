package com.pubsub.message.interchange.exception;

public class TopicDoesNotExistException extends RuntimeException {

    public TopicDoesNotExistException(String message) {
        super(message);
    }
}
