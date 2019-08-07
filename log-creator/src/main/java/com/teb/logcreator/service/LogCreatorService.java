package com.teb.logcreator.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.teb.logcreator.bus.procuder.MessageProducerService;
import com.teb.logcreator.model.CityNames;
import com.teb.logcreator.model.Log;
import com.teb.logcreator.model.LogType;
import com.teb.logcreator.utils.Utils;

@Service
public class LogCreatorService {

    @Autowired
    MessageProducerService messageProducerService;

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void sendLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        CityNames cityName = CityNames.getRandomCityName();
        Log log = new Log(strDate, LogType.getRandomLogType(), cityName, Utils.getDetail(cityName.name()));

        messageProducerService.sendCreateLogEvent(log);
    }
}
