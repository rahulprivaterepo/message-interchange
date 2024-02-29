package com.pubsub.message.interchange.exception;

public class SubscriberAlreadySignedUpException extends RuntimeException {

    public SubscriberAlreadySignedUpException(String message) {
        super(message);
    }
}
