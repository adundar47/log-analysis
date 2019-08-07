package com.teb.loglistener.bus.producer;

import com.teb.loglistener.model.Log;

public interface MessageProducerService {

    void sendLog(Log log);

}
