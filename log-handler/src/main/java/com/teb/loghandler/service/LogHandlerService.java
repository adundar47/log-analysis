package com.teb.loghandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.teb.loghandler.model.Log;
import com.teb.loghandler.repository.LogRepository;

@Service
public class LogHandlerService {

    @Autowired
    LogRepository         logRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public void logHandler(Log log) {
        logRepository.save(log);
        simpMessagingTemplate.convertAndSend(log);
    }
}
