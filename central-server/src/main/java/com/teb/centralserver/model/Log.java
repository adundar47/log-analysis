package com.teb.centralserver.model;

import java.io.Serializable;

public class Log implements Serializable {

    private static final long serialVersionUID = -7984236673438504502L;

    private String            timeStamp;

    private LogType           logLevel;

    private CityName          cityName;

    private String            logDetail;

    public Log() {
        super();
    }

    public Log(String timeStamp, LogType logLevel, CityName cityName, String logDetail) {
        super();
        this.timeStamp = timeStamp;
        this.logLevel = logLevel;
        this.cityName = cityName;
        this.logDetail = logDetail;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LogType getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogType logLevel) {
        this.logLevel = logLevel;
    }

    public CityName getCityName() {
        return cityName;
    }

    public void setCityName(CityName cityName) {
        this.cityName = cityName;
    }

    public String getLogDetail() {
        return logDetail;
    }

    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail;
    }

    @Override
    public String toString() {
        return "Log [timeStamp=" + timeStamp + ", logLevel=" + logLevel + ", cityName=" + cityName + ", logDetail="
                + logDetail + "]";
    }

}
