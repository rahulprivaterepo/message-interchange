package com.pubsub.message.interchange.constants;

public final class ExceptionMessageConstants {
    public static final String TOPIC_REGISTER_EXCEPTION = "Error occurred while trying to register topic in the DB";
    public static final String TOPIC_RETRIEVAL_EXCEPTION = "Error occurred while trying to retrieve topic from the DB";
    public static final String TOPIC_NOT_FOUND_EXCEPTION = "Topic Not Found in DB";
    public static final String UNAUTHORIZED_TO_PUBLISH_EXCEPTION = "Unauthorized to Publish. Topic Owned by a different Publisher";
    public static final String TOPIC_ALREADY_REGISTERED_EXCEPTION = "Topic already created. Duplication of Topics not allowed";
    public static final String SUBSCRIBER_ALREADY_REGISTERED_TO_THE_TOPIC_EXCEPTION = "Subscriber already registered to the topic.";
    public static final String MESSAGE_DOES_NOT_EXIST_EXCEPTION = "Message does not exist in the DB";
    public static final String NO_NEW_MESSAGE_IN_THE_TOPIC_EXCEPTION = "No new message in the Topic";
    public static final String SUBSCRIBER_NOT_LINKED_TO_TOPIC_EXCEPTION = "Subscriber did not subscribe to this Topic";
}
