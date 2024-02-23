package com.pubsub.message.interchange.exception;

public class TopicRetrievalException extends RuntimeException {

    public TopicRetrievalException(String message, Throwable e) {
        super(message, e);
    }
}
