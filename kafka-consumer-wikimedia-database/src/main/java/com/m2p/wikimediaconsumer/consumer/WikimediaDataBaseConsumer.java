package com.m2p.wikimediaconsumer.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaDataBaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaDataBaseConsumer.class);

    @KafkaListener(topics = "wikiTopic",groupId = "myGroup")
    public void consume(String eventMessage)
    {
        LOGGER.info(String.format("Message Received -> %s",eventMessage));
    }
}
