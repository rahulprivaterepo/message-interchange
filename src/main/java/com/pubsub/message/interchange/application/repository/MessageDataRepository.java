package com.pubsub.message.interchange.application.repository;

import com.pubsub.message.interchange.domain.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.pubsub.message.interchange.domain.request.Message;
import java.util.*;

public interface MessageDataRepository extends MongoRepository<MessageEntity, String> {
    Optional<MessageEntity> findByTopicId(String topicId);
    @Query("{'topicId': ?0}")
    void updateMessagesByTopicId(String topicId, Queue<Message> messages);
}
