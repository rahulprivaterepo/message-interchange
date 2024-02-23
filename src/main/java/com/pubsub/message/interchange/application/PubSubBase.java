package com.pubsub.message.interchange.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

public class PubSubBase {

    static final Map<String, Set<String>> PUBLISHER_TOPICS = new HashMap<>();
    static final Queue<String> MESSAGES_QUEUE = new LinkedList<>();
 }
