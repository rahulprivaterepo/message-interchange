package com.pubsub.message.interchange.application.repository;

import com.pubsub.message.interchange.domain.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MessageDataRepository extends MongoRepository<MessageEntity, String> {
    Optional<MessageEntity> findByMessageId(Long topicId);
}
