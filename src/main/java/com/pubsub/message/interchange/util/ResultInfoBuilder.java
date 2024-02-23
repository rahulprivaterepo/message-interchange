package com.pubsub.message.interchange.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pubsub.message.interchange.response.ResultInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultInfoBuilder {

    public static String buildResult(final ResultInfo resultInfo, final Object data) {
        JsonNode jsonObject = JsonUtil.createJsonNode();
        ((ObjectNode) jsonObject).set("resultInfo", JsonUtil.toJsonNode(resultInfo));
        ((ObjectNode) jsonObject).set("data", JsonUtil.toJsonNode(data));
        return JsonUtil.toJsonString(jsonObject);
    }
}
