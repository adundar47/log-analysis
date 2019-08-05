package com.teb.centralserver.utils;

import java.io.File;
import java.text.MessageFormat;

public class Utils {

    public static final String DETAIL    = "Hello-from-{0}";

    public static final String FILE_PATH = "C:\\Users\\AHMETPC\\Desktop\\testCode\\{0}.txt";

    public static double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }

    public static double getFileSizeKiloBytes(File file) {
        return (double) file.length() / 1024;
    }

    public static double getFileSizeBytes(File file) {
        return file.length();
    }

    public static String getDetail(String cityName) {
        return MessageFormat.format(DETAIL, cityName);
    }

    public static String getFilePath(int fileName) {
        return MessageFormat.format(FILE_PATH, fileName);
    }
}
