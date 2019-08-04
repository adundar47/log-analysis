package com.teb.logcreator.bus.procuder;

import com.teb.logcreator.model.Log;

public interface MessageProducerService {

	void sendCreateLogEvent(Log log);

}
