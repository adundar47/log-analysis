package com.teb.logcreator.utils;

import java.text.MessageFormat;

public class Utils {

    public static final String DETAIL = "Hello-from-{0}";

    public static String getDetail(String cityName) {
        return MessageFormat.format(DETAIL, cityName);
    }
}
