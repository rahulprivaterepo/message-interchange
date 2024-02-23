package com.pubsub.message.interchange.constants;

public final class ExceptionMessageConstants {
    public static final String TOPIC_REGISTER_EXCEPTION = "Error occurred while trying to register topic in the DB";
    public static final String TOPIC_RETRIEVAL_EXCEPTION = "Error occurred while trying to retrieve topic from the DB";
    public static final String TOPIC_NOT_FOUND_EXCEPTION = "Topic Not Found in DB";
    public static final String UNAUTHORIZED_TO_PUBLISH_EXCEPTION = "Unauthorized to Publish. Topic Owned by a different Publisher";
    public static final String TOPIC_ALREADY_REGISTERED_EXCEPTION = "Topic already created. Duplication of Topics not allowed";
}
