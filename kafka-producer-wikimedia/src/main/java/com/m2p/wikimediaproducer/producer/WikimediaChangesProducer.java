package com.m2p.wikimediaproducer.producer;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.m2p.wikimediaproducer.Changehandler.WikiMediaChangeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    //As we know we need the kafka Template for sending info to the topic
    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String Topic = "wikiTopic";
        //To read real time stream data from wikimedia we use event source
        EventHandler eventHandler = new WikiMediaChangeHandler(kafkaTemplate,Topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        //Creating a EventSource so that we can read the events
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
