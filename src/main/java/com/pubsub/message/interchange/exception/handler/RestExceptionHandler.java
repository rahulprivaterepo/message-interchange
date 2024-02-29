package com.pubsub.message.interchange.exception.handler;

import com.pubsub.message.interchange.exception.MessageDoesNotExistsException;
import com.pubsub.message.interchange.exception.NoNewMessageInTheTopicException;
import com.pubsub.message.interchange.exception.SubscriberAlreadySignedUpException;
import com.pubsub.message.interchange.exception.SubscriberNotLinkedToTopicException;
import com.pubsub.message.interchange.exception.TopicAlreadyRegisteredException;
import com.pubsub.message.interchange.exception.TopicDoesNotExistException;
import com.pubsub.message.interchange.exception.TopicRegisterException;
import com.pubsub.message.interchange.exception.TopicRetrievalException;
import com.pubsub.message.interchange.exception.UnauthorizedToPublishException;
import com.pubsub.message.interchange.response.ResultInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.MESSAGE_DOES_NOT_EXIST_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.NO_NEW_MESSAGE_IN_THE_TOPIC_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.TOPIC_ALREADY_REGISTERED_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.TOPIC_DOES_NOT_EXIST_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.TOPIC_REGISTER_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.TOPIC_RETRIEVAL_ERROR;
import static com.pubsub.message.interchange.constants.ErrorResultInfoConstants.UNAUTHORIZED_TO_PUBLISH_ERROR;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = TopicAlreadyRegisteredException.class)
    public ResponseEntity<ResultInfo> handleTopicAlreadyRegisteredException() {
        return new ResponseEntity<>(TOPIC_ALREADY_REGISTERED_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TopicDoesNotExistException.class)
    public ResponseEntity<ResultInfo> handleTopicDoesNotExistException() {
        return new ResponseEntity<>(TOPIC_DOES_NOT_EXIST_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TopicRegisterException.class)
    public ResponseEntity<ResultInfo> handleTopicRegisterException() {
        return new ResponseEntity<>(TOPIC_REGISTER_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TopicRetrievalException.class)
    public ResponseEntity<ResultInfo> handleTopicRetrievalException() {
        return new ResponseEntity<>(TOPIC_RETRIEVAL_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedToPublishException.class)
    public ResponseEntity<ResultInfo> handleUnauthorizedToPublishException() {
        return new ResponseEntity<>(UNAUTHORIZED_TO_PUBLISH_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SubscriberAlreadySignedUpException.class)
    public ResponseEntity<ResultInfo> handleSubscriberAlreadySignedUpException() {
        return new ResponseEntity<>(SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MessageDoesNotExistsException.class)
    public ResponseEntity<ResultInfo> handleMessageDoesNotExistsException() {
        return new ResponseEntity<>(MESSAGE_DOES_NOT_EXIST_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoNewMessageInTheTopicException.class)
    public ResponseEntity<ResultInfo> handleNoNewMessageInTheTopicException() {
        return new ResponseEntity<>(NO_NEW_MESSAGE_IN_THE_TOPIC_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SubscriberNotLinkedToTopicException.class)
    public ResponseEntity<ResultInfo> handleSubscriberNotLinkedToTopicException() {
        return new ResponseEntity<>(NO_NEW_MESSAGE_IN_THE_TOPIC_ERROR, HttpStatus.BAD_REQUEST);
    }

}
