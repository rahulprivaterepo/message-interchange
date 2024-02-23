package com.pubsub.message.interchange.constants;

import com.pubsub.message.interchange.response.ResultInfo;

import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_ALREADY_REGISTERED_EXCEPTION;

public final class ErrorResultInfoConstants {

    public static final ResultInfo TOPIC_ALREADY_REGISTERED_ERROR = ResultInfo.builder()
            .codeId("501")
            .code("TOPIC_ALREADY_REGISTERED_ERROR")
            .message(TOPIC_ALREADY_REGISTERED_EXCEPTION)
            .status("S").build();
}
