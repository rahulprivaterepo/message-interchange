package com.pubsub.message.interchange.constants;

import com.pubsub.message.interchange.response.ResultInfo;

public final class ResultInfoConstants {
    public static final ResultInfo SUCCESS = ResultInfo.builder()
            .codeId("001")
            .code("SUCCESS")
            .message("Successful Request")
            .status("S").build();
}
