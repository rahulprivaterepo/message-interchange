package com.pubsub.message.interchange.domain.entity;

import com.pubsub.message.interchange.domain.request.Subscriber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Document(collection = "topic")
@Data
@Builder
public class TopicEntity {
    @Id
    private String id;
    private String topicName;
    private String publisherId;
    private String publisherName;
    private List<Subscriber> subscriber;
}
