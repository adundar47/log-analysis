package com.teb.loglistener.model;

public enum OSType {

    WINDOWS("WINDOWS"), OSX("OSX"), LINUX("LINUX");

    private final String type;

    private OSType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static OSType fromString(String text) {
        if (text != null) {
            for (OSType osType : OSType.values()) {
                if (text.equals(osType.type)) {
                    return osType;
                }
            }
        }
        return null;
    }

}
