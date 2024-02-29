package com.pubsub.message.interchange.exception;

public class SubscriberNotLinkedToTopicException extends RuntimeException {

    public SubscriberNotLinkedToTopicException(String message) {
        super(message);
    }
}
