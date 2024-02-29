package com.pubsub.message.interchange.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pubsub.message.interchange.domain.request.TopicRequest;
import com.pubsub.message.interchange.domain.request.PublisherRequest;
import com.pubsub.message.interchange.domain.request.MessageRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TopicRegisterRequest {

    @Valid
    @NotNull
    @JsonProperty("topic")
    private TopicRequest topic;

    @Valid
    @NotNull
    @JsonProperty("publisher")
    private PublisherRequest publisher;

    @Valid
    @JsonProperty("message")
    private MessageRequest message;
}
