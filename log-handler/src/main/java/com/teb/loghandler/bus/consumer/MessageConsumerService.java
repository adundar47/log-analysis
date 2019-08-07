package com.teb.loghandler.bus.consumer;

import java.io.IOException;

import com.teb.loghandler.model.Log;

public interface MessageConsumerService {

	void handleLog(Log log) throws IOException;

}
