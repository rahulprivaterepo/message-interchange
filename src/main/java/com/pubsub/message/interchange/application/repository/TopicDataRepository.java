package com.pubsub.message.interchange.application.repository;

import com.pubsub.message.interchange.domain.entity.TopicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TopicDataRepository extends MongoRepository<TopicEntity, String> {
    Optional<TopicEntity> findByTopicId(Long topicId);
}
