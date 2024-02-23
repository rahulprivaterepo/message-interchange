package com.pubsub.message.interchange.application;

import com.pubsub.message.interchange.application.repository.MessageDataRepository;
import com.pubsub.message.interchange.application.repository.TopicDataRepository;
import com.pubsub.message.interchange.domain.entity.MessageEntity;
import com.pubsub.message.interchange.domain.entity.TopicEntity;
import com.pubsub.message.interchange.domain.request.Message;
import com.pubsub.message.interchange.domain.request.Publisher;
import com.pubsub.message.interchange.domain.request.Topic;
import com.pubsub.message.interchange.exception.TopicAlreadyRegisteredException;
import com.pubsub.message.interchange.exception.TopicDoesNotExistException;
import com.pubsub.message.interchange.exception.TopicRegisterException;
import com.pubsub.message.interchange.exception.TopicRetrievalException;
import com.pubsub.message.interchange.exception.UnauthorizedToPublishException;
import com.pubsub.message.interchange.util.RandomUUID;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.TOPIC_ALREADY_REGISTERED_ERROR;
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

    public void registerPublisherWithTopic(@NotNull final Topic topic, @NotNull  final Publisher publisher) {

        log.info("Register Publisher : {} with Topic: {}", publisher.getPublisherName(), topic.getTopicName());

        var topicInDB = topicDataRepository.findByTopicName(topic.getTopicName());

        if (topicInDB.isPresent()) {
            if (StringUtils.isNotBlank((topicInDB.get().getPublisherId()))) {
                throw new TopicAlreadyRegisteredException(TOPIC_ALREADY_REGISTERED_ERROR);
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
            @NotNull final Topic topic,
            @NotNull final Publisher publisher
    ) {
        return TopicEntity.builder()
                .topicName(topic.getTopicName())
                .publisherId(RandomUUID.generateRandomUUID().toString())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    public void publishMessageToTopic(
            @NotNull final String topicId,
            @NotNull final String publisherId,
            @NotNull final Message message) {
        Optional<TopicEntity> topicEntity;
        try {
            topicEntity = topicDataRepository.findById(topicId);
        } catch (Exception e) {
            log.error("Topic Registration Failed: {}", e.getMessage(), e);
            throw new TopicRetrievalException(TOPIC_RETRIEVAL_EXCEPTION, e);
        }

        if (topicEntity.isEmpty()) {
            throw new TopicDoesNotExistException(TOPIC_NOT_FOUND_EXCEPTION);
        }

        var topicOwnerId = topicEntity.get().getPublisherId();
        var topicOwnerName = topicEntity.get().getPublisherName();

        if (!Objects.equals(topicOwnerId, publisherId)) {
            log.error("The Topic is owned by Publisher: {} with Publisher Id: {}, hence Publisher: {} can't push message to this topic",
                    topicOwnerName, topicOwnerId, publisherId);
            throw new UnauthorizedToPublishException(UNAUTHORIZED_TO_PUBLISH_EXCEPTION);
        }
        publishMessage(topicId, message);
    }

    private void publishMessage(@NotNull final String topicId, @NotNull final Message message) {
        var messageEntityDb = messageDataRepository.findByTopicId(topicId);

        if (messageEntityDb.isEmpty()) {
            Queue<Message> messageQueue = new LinkedList<>();
            messageQueue.offer(message);

            MessageEntity messageEntity = MessageEntity.builder()
                    .topicId(topicId)
                    .messages(messageQueue).build();

            messageDataRepository.save(messageEntity);
        } else {
            var messageQueue = messageEntityDb.get().getMessages();
            messageQueue.offer(message);
            messageDataRepository.updateMessagesByTopicId(topicId, messageQueue);
        }
    }
}
