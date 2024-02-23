package com.pubsub.message.interchange.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic {
    @NotNull
    @Size(min = 2, max = 10 , message = "topic name should not exceed 10 characters and contain at least 2 characters")
    private String topicName;
}
