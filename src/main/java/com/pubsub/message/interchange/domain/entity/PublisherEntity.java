package com.pubsub.message.interchange.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publisher")
@Data
@Builder
public class PublisherEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Long publisherId;
    private String publisherName;
}
