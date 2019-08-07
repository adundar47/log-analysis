package com.teb.loghandler.model;

public enum CityName {
    ISTANBUL("ISTANBUL"), TOKYO("TOKYO"), MOSKOW("MOSKOW"), BEIJING("BEIJING"), LONDON("LONDON");

    private final String type;

    private CityName(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static CityName fromString(String text) {
        if (text != null) {
            for (CityName eventType : CityName.values()) {
                if (text.equals(eventType.type)) {
                    return eventType;
                }
            }
        }
        return null;
    }

    public static CityName getRandomCityName() {
        return values()[(int) (Math.random() * values().length)];
    }
}
