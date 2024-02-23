package com.pubsub.message.interchange.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pubsub.message.interchange.domain.request.Topic;
import com.pubsub.message.interchange.domain.request.Publisher;
import com.pubsub.message.interchange.domain.request.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TopicRegisterRequest {

    @Valid
    @NotNull
    @JsonProperty("topic")
    private Topic topic;

    @Valid
    @NotNull
    @JsonProperty("publisher")
    private Publisher publisher;

    @Valid
    @JsonProperty("message")
    private Message message;
}
