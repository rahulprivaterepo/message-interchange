package com.pubsub.message.interchange.exception;

import com.pubsub.message.interchange.response.ResultInfo;

public class TopicAlreadyRegisteredException extends BaseException {

    public TopicAlreadyRegisteredException(ResultInfo resultInfo) {
        super(resultInfo);
    }
}
