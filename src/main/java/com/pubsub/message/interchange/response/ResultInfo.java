package com.pubsub.message.interchange.response;

import com.pubsub.message.interchange.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.JSONObject;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class ResultInfo implements Serializable {
    private String codeId;
    private String code;
    private String message;
    private String status;


    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codeId", codeId);
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        jsonObject.put("status", status);
        return jsonObject.toJSONString();
    }
}
