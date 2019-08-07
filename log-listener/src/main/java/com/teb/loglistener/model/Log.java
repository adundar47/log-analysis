package com.teb.loglistener.model;

public class Log {

    private String   timeStamp;

    private LogType  logLevel;

    private CityNames cityName;

    private String   logDetail;

    public Log(String timeStamp, LogType logLevel, CityNames cityName, String logDetail) {
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

    public CityNames getCityName() {
        return cityName;
    }

    public void setCityName(CityNames cityName) {
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
