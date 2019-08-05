package com.teb.logcreator.bus.procuder;

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

import com.teb.logcreator.model.Log;

@Service
public class KafkaMessageProducerManager implements MessageProducerService {

    private static final Logger           LOG = LoggerFactory.getLogger(KafkaMessageProducerManager.class);

    @Value("${kafka.topic.createLog}")
    private String                        createLogTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Override
    public void sendCreateLogEvent(Log log) {
        LOG.debug("[sendCreateLogEvent] Log object is sending.. -> {}", log);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(createLogTopic, log);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendCreateLogEvent] sent message='{}' with offset={}", log,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendCreateLogEvent] unable to send message='{}'", log, ex);
            }

        });

    }
}
