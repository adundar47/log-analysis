package com.teb.loglistener.bus.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.teb.loglistener.model.Log;

@Service
public class KafkaMessageProducerManager implements MessageProducerService {

    private static final Logger           LOG = LoggerFactory.getLogger(KafkaMessageProducerManager.class);

    @Value("${kafka.topic.readingLog}")
    private String                        logTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Override
    public void sendLogEvent(Log log) {
        LOG.debug("[sendLogEvent] Log object is sending.. -> {}", log);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(logTopic, log);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendLogEvent] sent message='{}' with offset={}", log,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendLogEvent] unable to send message='{}'", log, ex);
            }

        });

    }
}
