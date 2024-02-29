package com.pubsub.message.interchange.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscriber")
@Data
@Builder
public class SubscriberEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Long subscriberId;
    private String subscriberName;
}
