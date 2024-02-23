package com.pubsub.message.interchange.application;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class MessageApplication extends PubSubBase {

    public void publishMessageToTopic(
            @NotNull final String topicName,
            @NotNull final String publisherName) {

        Set<String> publisherList = PUBLISHER_TOPICS.get(topicName);

        if (publisherList == null || publisherList.isEmpty()) {
            throw new RuntimeException("This Topic is not owned by any publisher");
        }

        if(!publisherList.contains(publisherName)) {
            throw new RuntimeException("Unauthorized to publish to topic owned by another user");
        }
    }


}
