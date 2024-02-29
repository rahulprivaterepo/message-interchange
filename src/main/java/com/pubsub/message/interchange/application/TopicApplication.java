package com.pubsub.message.interchange.application;

import com.pubsub.message.interchange.application.repository.MessageDataRepository;
import com.pubsub.message.interchange.application.repository.SubscriberDataRepository;
import com.pubsub.message.interchange.application.repository.TopicDataRepository;
import com.pubsub.message.interchange.domain.entity.MessageEntity;
import com.pubsub.message.interchange.domain.entity.SubscriberEntity;
import com.pubsub.message.interchange.domain.entity.TopicEntity;
import com.pubsub.message.interchange.domain.request.MessageRequest;
import com.pubsub.message.interchange.domain.request.PublisherRequest;
import com.pubsub.message.interchange.domain.request.SubscriberRequest;
import com.pubsub.message.interchange.domain.request.TopicRequest;
import com.pubsub.message.interchange.exception.MessageDoesNotExistsException;
import com.pubsub.message.interchange.exception.NoNewMessageInTheTopicException;
import com.pubsub.message.interchange.exception.SubscriberAlreadySignedUpException;
import com.pubsub.message.interchange.exception.SubscriberNotLinkedToTopicException;
import com.pubsub.message.interchange.exception.TopicAlreadyRegisteredException;
import com.pubsub.message.interchange.exception.TopicDoesNotExistException;
import com.pubsub.message.interchange.exception.TopicRegisterException;
import com.pubsub.message.interchange.exception.TopicRetrievalException;
import com.pubsub.message.interchange.exception.UnauthorizedToPublishException;
import com.pubsub.message.interchange.util.DateTimeUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.MESSAGE_DOES_NOT_EXIST_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.NO_NEW_MESSAGE_IN_THE_TOPIC_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.SUBSCRIBER_NOT_LINKED_TO_TOPIC_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_ALREADY_REGISTERED_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_NOT_FOUND_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_REGISTER_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.TOPIC_RETRIEVAL_EXCEPTION;
import static com.pubsub.message.interchange.constants.ExceptionMessageConstants.UNAUTHORIZED_TO_PUBLISH_EXCEPTION;

@Slf4j
@AllArgsConstructor
@Service
public class TopicApplication {

    private final TopicDataRepository topicDataRepository;
    private final MessageDataRepository messageDataRepository;
    private final SubscriberDataRepository subscriberDataRepository;

    public void registerPublisherWithTopic(
            @NotNull final TopicRequest topic,
            @NotNull  final PublisherRequest publisher
    ) {

        log.info("Register Publisher : {} with Topic: {}", publisher.getPublisherName(), topic.getTopicName());

        var topicInDB = topicDataRepository.findByTopicId(topic.getTopicId());

        if (topicInDB.isPresent()) {
            if (topicInDB.get().getPublisherId() != null) {
               throw new TopicAlreadyRegisteredException(TOPIC_ALREADY_REGISTERED_EXCEPTION);
            }
        }

        // Construct Topic Entity
        var topicEntity = buildTopicEntity(topic, publisher);

        try {
            // Register Topic
            topicDataRepository.save(topicEntity);
        } catch (Exception e) {
            log.error("Topic Registration Failed: {}", e.getMessage(), e);
            throw new TopicRegisterException(TOPIC_REGISTER_EXCEPTION, e);
        }
    }

    private TopicEntity buildTopicEntity(
            @NotNull final TopicRequest topic,
            @NotNull final PublisherRequest publisher
    ) {
        return TopicEntity.builder()
                .topicId(topic.getTopicId())
                .topicName(topic.getTopicName())
                .publisherId(publisher.getPublisherId())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    private SubscriberEntity buildSubscriberEntity(
            @NotNull final SubscriberRequest subscriber
    ) {
        return SubscriberEntity.builder()
                .subscriberId(subscriber.getSubscriberId())
                .subscriberName(subscriber.getSubscriberName()).build();
    }

    public void publishMessageToTopic(
            @NotNull final Long topicId,
            @NotNull final Long publisherId,
            @NotNull final MessageRequest message
    ) {
        // Fetch Topic Details
        var topicEntity = fetchTopicDetails(topicId);
        var topicOwnerId = topicEntity.getPublisherId();
        var topicOwnerName = topicEntity.getPublisherName();

        if (!Objects.equals(topicOwnerId, publisherId)) {
            log.error("The Topic is owned by Publisher: {} with Publisher Id: {}, hence Publisher: {} can't push message to this topic",
                    topicOwnerName, topicOwnerId, publisherId);
            throw new UnauthorizedToPublishException(UNAUTHORIZED_TO_PUBLISH_EXCEPTION);
        }
        publishMessage(topicId, message);
    }

    private void publishMessage(
            @NotNull final Long topicId,
            @NotNull final MessageRequest message
    ) {
        var topicEntityDb = topicDataRepository.findByTopicId(topicId);
        try {
            if (topicEntityDb.isEmpty()) {
                throw new TopicDoesNotExistException(TOPIC_NOT_FOUND_EXCEPTION);
            } else {
                if (isNullOrEmpty(topicEntityDb.get().getMessages())) {
                     LinkedList<MessageRequest> messageQueue = new LinkedList<>();
                     MessageRequest messageDto = MessageRequest.builder()
                             .messageId(message.getMessageId())
                             .createdAt(DateTimeUtil.getCurrentUtcDateTime())
                             .message(message.getMessage())
                             .build();
                    // message.setCreatedAt(DateTimeUtil.getCurrentUtcDateTime());
                     messageQueue.add(messageDto);
                     topicEntityDb.get().setMessages(messageQueue);
                } else {
                    message.setCreatedAt(DateTimeUtil.getCurrentUtcDateTime());
                    topicEntityDb.get().getMessages().add(message);
                }
                topicDataRepository.save(topicEntityDb.get());
            }

            MessageEntity messageEntity = MessageEntity.builder()
                    .messageId(message.getMessageId())
                    .message(message.getMessage())
                    .ack(0)
                    .createdAt(ZonedDateTime.now(ZoneOffset.of("+0900"))
                            .withZoneSameInstant(ZoneOffset.UTC)
                            .toLocalDateTime())
                    .build();

            messageDataRepository.save(messageEntity);
        } catch (Exception e) {
            log.error("Exception Occurred while publishing message to topic : {}", topicId, e);
        }
    }

    public void subscribeToTopic(
            @NotNull final Long topicId,
            @NotNull final SubscriberRequest subscriber) {
        // Fetch Topic Entity
        var topicEntity = fetchTopicDetails(topicId);

        if (topicEntity.getSubscribers() == null) {
            Set<SubscriberRequest> subscriberList = new HashSet<>();
            subscriber.setCreatedAt(DateTimeUtil.getCurrentUtcDateTime());
            subscriberList.add(subscriber);
            topicEntity.setSubscribers(subscriberList);
        } else {
            var existingSubscribers = topicEntity.getSubscribers();
            if (existingSubscribers.contains(subscriber)) {
                throw new SubscriberAlreadySignedUpException(SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_EXCEPTION);
            }
            subscriber.setCreatedAt(DateTimeUtil.getCurrentUtcDateTime());
            topicEntity.getSubscribers().add(subscriber);
        }
        topicDataRepository.save(topicEntity);
        var subscriberEntity = buildSubscriberEntity(subscriber);
        subscriberDataRepository.save(subscriberEntity);
    }

    public void ackMessage(@NotNull final Long messageId, Long subscriberId) {
        var messageEntityDb = messageDataRepository.findByMessageId(messageId);
        if (messageEntityDb.isEmpty()) {
            throw new MessageDoesNotExistsException(MESSAGE_DOES_NOT_EXIST_EXCEPTION);
        }
        var messageEntity = messageEntityDb.get();

        messageEntity.setAck(1);
        messageDataRepository.save(messageEntity);

        var topicEntityDb = topicDataRepository.findByTopicId(messageEntityDb.get().getTopicId());
        if (topicEntityDb.isEmpty()) {
            throw new TopicDoesNotExistException(TOPIC_NOT_FOUND_EXCEPTION);
        }
        var topicEntity = topicEntityDb.get();


        topicEntity.getMessages().removeFirst();
        topicDataRepository.save(topicEntity);
    }

    public String getMessage(
            @NotNull final Long subscriberId,
            @NotNull final Long topicId
    ) {
        var topicInDb = topicDataRepository.findByTopicId(topicId);

        if (topicInDb.isEmpty()) {
            throw new TopicDoesNotExistException(TOPIC_NOT_FOUND_EXCEPTION);
        }

        var topicEntity = topicInDb.get();

        var isSubscriberExist = isSubscriberExist(topicEntity, subscriberId);

        if (!isSubscriberExist) {
            throw new SubscriberNotLinkedToTopicException(SUBSCRIBER_NOT_LINKED_TO_TOPIC_EXCEPTION);
        }

        if (topicEntity.getMessages() == null) {
            throw new MessageDoesNotExistsException(MESSAGE_DOES_NOT_EXIST_EXCEPTION);
        }

        if (topicEntity.getMessages().isEmpty()) {
            log.info("No New Messages");
            throw new NoNewMessageInTheTopicException(NO_NEW_MESSAGE_IN_THE_TOPIC_EXCEPTION);
        }

        var createdAtSubscriber = topicEntity.getSubscribers().stream()
                .filter(subscriber -> subscriber.getSubscriberId().equals(subscriberId))
                .map(SubscriberRequest::getCreatedAt)
                .findFirst().orElse(null);

        if (createdAtSubscriber == null) {
            throw new RuntimeException("Created At is Null");
        }

        var messageList = topicInDb.get().getMessages();

        for (MessageRequest message : messageList) {
            var compare = createdAtSubscriber.compareTo(message.getCreatedAt());
            if (compare < 0) {
                return message.getMessage();
            }
        }
        return null;
    }

    private TopicEntity fetchTopicDetails(@NotNull final Long topicId) {
        Optional<TopicEntity> topicEntity;
        try {
            topicEntity = topicDataRepository.findByTopicId(topicId);
        } catch (Exception e) {
            log.error("Topic Registration Failed: {}", e.getMessage(), e);
            throw new TopicRetrievalException(TOPIC_RETRIEVAL_EXCEPTION, e);
        }

        if (topicEntity.isEmpty()) {
            throw new TopicDoesNotExistException(TOPIC_NOT_FOUND_EXCEPTION);
        }
        return topicEntity.get();
    }

    private boolean isNullOrEmpty(LinkedList<MessageRequest> collection) {
        return collection == null || collection.isEmpty();
    }

    private boolean isSubscriberExist(
            @NotNull final TopicEntity topicEntity,
            @NotNull final Long subscriberId) {
        for (SubscriberRequest subscriber : topicEntity.getSubscribers()) {
            if (subscriber.getSubscriberId().equals(subscriberId)) {
                return true;
            }
        }
        return false;
    }
}
