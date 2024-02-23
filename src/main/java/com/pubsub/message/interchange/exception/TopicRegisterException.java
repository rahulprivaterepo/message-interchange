package com.pubsub.message.interchange.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class TopicRegisterException extends RuntimeException {

    public TopicRegisterException(String message, Throwable e) {
        super(message, e);
    }
}
