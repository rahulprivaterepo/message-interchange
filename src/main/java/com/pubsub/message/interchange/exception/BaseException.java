package com.pubsub.message.interchange.exception;

import com.pubsub.message.interchange.response.ResultInfo;

public class BaseException extends RuntimeException {

    public BaseException(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
    }
}
