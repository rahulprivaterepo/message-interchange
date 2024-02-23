package com.pubsub.message.interchange.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.pubsub.message.interchange.domain.request.Message;

import java.util.*;

@Document(collection = "messages")
@Data
@Builder
public class MessageEntity {
    @Id
    private long id;
    private String topicId;
    private Queue<Message> messages;
}
