package com.pubsub.message.interchange.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {
    @NotNull
    private Long messageId;
    @NotNull
    @Size(max = 128 * 1024, message = "Message size cannot exceed 128 KB")
    private String message;
    private LocalDateTime createdAt;
}
