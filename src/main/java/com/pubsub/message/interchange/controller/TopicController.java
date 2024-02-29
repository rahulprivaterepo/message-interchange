package com.pubsub.message.interchange.controller;

import com.pubsub.message.interchange.application.TopicApplication;
import com.pubsub.message.interchange.constants.ResultInfoConstants;
import com.pubsub.message.interchange.controller.request.TopicRegisterRequest;
import com.pubsub.message.interchange.domain.request.MessageRequest;
import com.pubsub.message.interchange.domain.request.SubscriberRequest;
import com.pubsub.message.interchange.response.ResultInfo;
import com.pubsub.message.interchange.util.ResultInfoBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/topic")
public class TopicController {

    private final TopicApplication topicApplication;

    @PostMapping("register")
    public ResponseEntity<String> registerTopic(
            @Valid @NotNull @RequestBody TopicRegisterRequest topicRegisterRequest
    ) {
        topicApplication.registerPublisherWithTopic(topicRegisterRequest.getTopic(), topicRegisterRequest.getPublisher());
        return createSuccessResponse(null);
    }

    @PostMapping("publish/{topicId}")
    public ResponseEntity<String> publishMessageToTopic(
            @Valid @NotNull @PathVariable Long topicId,
            @RequestParam(value = "publisherId") Long publisherId,
            @Valid @NotNull @RequestBody MessageRequest message
    ) {
        topicApplication.publishMessageToTopic(topicId, publisherId, message);
        return createSuccessResponse(null);
    }

    @PostMapping("subscribe/{topicId}")
    public ResponseEntity<String> subscribeToTopic(
            @Valid @NotNull @PathVariable Long topicId,
            @Valid @NotNull @RequestBody SubscriberRequest subscriber
    ) {
        topicApplication.subscribeToTopic(topicId, subscriber);
        return createSuccessResponse(null);
    }

    @PostMapping("ack/{messageId}")
    public ResponseEntity<String> ackMessage(
            @Valid @NotNull @PathVariable Long messageId,
            @RequestParam("subscriberId") Long subscriberId
    ) {
        topicApplication.ackMessage(messageId, subscriberId);
        return createSuccessResponse(null);
    }

    @GetMapping("getMessage/{subscriberId}")
    public ResponseEntity<String> getMessage(
            @Valid @NotNull @PathVariable Long subscriberId,
            @RequestParam("topicId") Long topicId
    ) {
        var message = topicApplication.getMessage(subscriberId, topicId);
        return createSuccessResponse(message);
    }

    /**
     * Data would be passed in cases where App layer passes some information
     */
    private ResponseEntity<String> createSuccessResponse(Object data) {
        ResultInfo success = ResultInfoConstants.SUCCESS;
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ResultInfoBuilder.buildResult(success, data));
    }
}
