package com.pubsub.message.interchange.controller;

import com.pubsub.message.interchange.application.TopicApplication;
import com.pubsub.message.interchange.constants.ResultInfoConstants;
import com.pubsub.message.interchange.response.ResultInfo;
import com.pubsub.message.interchange.controller.request.TopicRegisterRequest;
import com.pubsub.message.interchange.domain.request.Message;
import com.pubsub.message.interchange.util.ResultInfoBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            @Valid @NotNull @PathVariable String topicId,
            @RequestParam(value = "publisherId") String publisherId,
            @Valid @NotNull @RequestBody Message message
    ) {
        topicApplication.publishMessageToTopic(topicId, publisherId, message);
        return createSuccessResponse(null);
    }

    /**
     * Data would be passed in cases where App layer passes some information
     */
    private ResponseEntity<String> createSuccessResponse(final Object data) {
        ResultInfo success = ResultInfoConstants.SUCCESS;
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ResultInfoBuilder.buildResult(success, null));
    }
}
