package com.pubsub.message.interchange.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicRequest {
    @NotNull
    private Long topicId;
    @NotNull
    @Size(min = 2, max = 10 , message = "topic name should not exceed 10 characters and contain at least 2 characters")
    private String topicName;
}
