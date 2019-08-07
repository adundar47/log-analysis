package com.teb.centralserver.bus.consumer;

import java.io.IOException;

import com.teb.centralserver.exception.NotFoundException;
import com.teb.centralserver.model.Log;

public interface MessageConsumerService {

    void handleLogCreateEvent(Log log) throws IOException, NotFoundException;

}
