package com.pubsub.message.interchange.domain.dto;

import com.pubsub.message.interchange.domain.request.base.MessageBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @NotNull
    private Long messageId;
    @NotNull
    private LocalDateTime createdAt;
}
