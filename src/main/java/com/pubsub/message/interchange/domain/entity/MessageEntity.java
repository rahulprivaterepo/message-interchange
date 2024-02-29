package com.pubsub.message.interchange.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Document(collection = "messages")
@Data
@Builder
public class MessageEntity {
    @Id
    private String id;
    @Indexed
    private Long messageId;
    private Long topicId;
    private String message;
    private int ack;
    @NotNull
    private LocalDateTime createdAt;
}
