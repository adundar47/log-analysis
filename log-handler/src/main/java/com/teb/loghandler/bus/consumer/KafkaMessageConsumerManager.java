package com.teb.loghandler.bus.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.teb.loghandler.model.Log;
import com.teb.loghandler.service.LogHandlerService;

@Service
public class KafkaMessageConsumerManager implements MessageConsumerService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LogHandlerService logHandlerService;

	@KafkaListener(topics = "${kafka.topic.readingLog}")
	@Override
	public void handleLog(Log log) throws IOException {
		LOGGER.debug("Log Received -> {}", log.toString());
		LOGGER.debug("[handleLog]: handleLog is processing. Log: {}", log);
		logHandlerService.logHandler(log);
	}

}
