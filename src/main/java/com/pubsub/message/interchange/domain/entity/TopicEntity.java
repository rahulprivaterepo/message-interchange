package com.pubsub.message.interchange.domain.entity;

import com.pubsub.message.interchange.domain.request.MessageRequest;
import com.pubsub.message.interchange.domain.request.SubscriberRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.Set;

@EqualsAndHashCode
@Document(collection = "topic")
@Data
@Builder
public class TopicEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Long topicId;
    private String topicName;
    private Long publisherId;
    private String publisherName;
    private Set<SubscriberRequest> subscribers;
    private LinkedList<MessageRequest> messages;
}
