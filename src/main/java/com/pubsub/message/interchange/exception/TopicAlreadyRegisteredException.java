package com.pubsub.message.interchange.exception;

public class TopicAlreadyRegisteredException extends RuntimeException {

    public TopicAlreadyRegisteredException(String message) {
        super(message);
    }
}
