package com.pubsub.message.interchange.application.repository;

import com.pubsub.message.interchange.domain.entity.SubscriberEntity;
import com.pubsub.message.interchange.domain.entity.TopicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriberDataRepository extends MongoRepository<SubscriberEntity, String> {

}
