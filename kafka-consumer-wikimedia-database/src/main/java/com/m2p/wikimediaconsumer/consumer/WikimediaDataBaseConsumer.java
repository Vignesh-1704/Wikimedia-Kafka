package com.m2p.wikimediaconsumer.consumer;


import com.m2p.wikimediaconsumer.entity.WikimediaData;
import com.m2p.wikimediaconsumer.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaDataBaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaDataBaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(topics = "wikiTopic",groupId = "myGroup")
    public void consume(String eventMessage)
    {
        LOGGER.info(String.format("Message Received -> %s",eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);
    }
}
