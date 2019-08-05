package com.teb.centralserver.bus.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.teb.centralserver.model.Log;
import com.teb.centralserver.service.FileCreateService;

@Service
public class KafkaMessageConsumerManager implements MessageConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileCreateService    fileCreateService;

    @KafkaListener(topics = "${kafka.topic.createLog}")
    @Override
    public void handleLogCreateEvent(Log log) throws IOException {
        LOGGER.debug("Log Received -> {}", log.toString());
        LOGGER.debug("[handleLogCreateEvent]: handleLogCreateEvent is processing. Log: {}", log);
        fileCreateService.create(log);
    }

}
