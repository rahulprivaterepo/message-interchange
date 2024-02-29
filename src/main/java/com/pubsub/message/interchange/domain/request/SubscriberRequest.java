package com.pubsub.message.interchange.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "subscriberId")
public class SubscriberRequest {
    @NotNull
    private Long subscriberId;

    @NotNull
    @Size(min = 2, max = 10 , message = "subscriber name should have at least 2 characters & not exceed 10 characters")
    private String subscriberName;

    private LocalDateTime createdAt;
}
