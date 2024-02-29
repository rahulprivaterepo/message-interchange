package com.pubsub.message.interchange.constants;

import com.pubsub.message.interchange.response.ResultInfo;

import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_ALREADY_REGISTERED_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_NOT_FOUND_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_REGISTER_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_RETRIEVAL_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.UNAUTHORIZED_TO_PUBLISH_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.MESSAGE_DOES_NOT_EXIST_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.NO_NEW_MESSAGE_IN_THE_TOPIC_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.*;

public final class ErrorResultInfoConstants {

    public static final ResultInfo TOPIC_ALREADY_REGISTERED_ERROR = ResultInfo.builder()
            .codeId("501")
            .code("TOPIC_ALREADY_REGISTERED_ERROR")
            .message(TOPIC_ALREADY_REGISTERED_EXCEPTION)
            .status("F").build();

    public static final ResultInfo TOPIC_DOES_NOT_EXIST_ERROR = ResultInfo.builder()
            .codeId("502")
            .code("TOPIC_DOES_NOT_EXIST_ERROR")
            .message(TOPIC_NOT_FOUND_EXCEPTION)
            .status("F").build();

    public static final ResultInfo TOPIC_REGISTER_ERROR = ResultInfo.builder()
            .codeId("503")
            .code("TOPIC_REGISTER_ERROR")
            .message(TOPIC_REGISTER_EXCEPTION)
            .status("F").build();

    public static final ResultInfo TOPIC_RETRIEVAL_ERROR = ResultInfo.builder()
            .codeId("504")
            .code("TOPIC_RETRIEVAL_ERROR")
            .message(TOPIC_RETRIEVAL_EXCEPTION)
            .status("F").build();

    public static final ResultInfo UNAUTHORIZED_TO_PUBLISH_ERROR = ResultInfo.builder()
            .codeId("505")
            .code("UNAUTHORIZED_TO_PUBLISH_ERROR")
            .message(UNAUTHORIZED_TO_PUBLISH_EXCEPTION)
            .status("F").build();

    public static final ResultInfo SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_ERROR = ResultInfo.builder()
            .codeId("506")
            .code("SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_ERROR")
            .message(SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_EXCEPTION)
            .status("F").build();

    public static final ResultInfo MESSAGE_DOES_NOT_EXIST_ERROR = ResultInfo.builder()
            .codeId("507")
            .code("MESSAGE_DOES_NOT_EXIST_ERROR")
            .message(MESSAGE_DOES_NOT_EXIST_EXCEPTION)
            .status("F").build();

    public static final ResultInfo NO_NEW_MESSAGE_IN_THE_TOPIC_ERROR = ResultInfo.builder()
            .codeId("507")
            .code("NO_NEW_MESSAGE_IN_THE_TOPIC_ERROR")
            .message(NO_NEW_MESSAGE_IN_THE_TOPIC_EXCEPTION)
            .status("F").build();

    public static final ResultInfo SUBSCRIBER_NOT_LINKED_TO_TOPIC_ERROR = ResultInfo.builder()
            .codeId("507")
            .code("SUBSCRIBER_NOT_LINKED_TO_TOPIC_ERROR")
            .message(SUBSCRIBER_NOT_LINKED_TO_TOPIC_EXCEPTION)
            .status("F").build();
}
