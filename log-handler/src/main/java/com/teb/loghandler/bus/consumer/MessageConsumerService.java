package com.teb.loghandler.bus.consumer;

import java.io.IOException;

import com.teb.loghandler.model.Log;

public interface MessageConsumerService {

    void handleLogCreateEvent(Log log) throws IOException;

}
