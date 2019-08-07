package com.teb.loghandler.model;

public enum CityNames {
    ISTANBUL("ISTANBUL"), TOKYO("TOKYO"), MOSKOW("MOSKOW"), BEIJING("BEIJING"), LONDON("LONDON");

    private final String type;

    private CityNames(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static CityNames fromString(String text) {
        if (text != null) {
            for (CityNames eventType : CityNames.values()) {
                if (text.equals(eventType.type)) {
                    return eventType;
                }
            }
        }
        return null;
    }

    public static CityNames getRandomCityName() {
        return values()[(int) (Math.random() * values().length)];
    }
}
