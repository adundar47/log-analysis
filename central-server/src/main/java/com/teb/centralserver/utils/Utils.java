package com.teb.centralserver.utils;

import java.io.File;
import java.text.MessageFormat;

import com.teb.centralserver.exception.NotFoundException;
import com.teb.centralserver.model.OSType;

public class Utils {

    public static final String DETAIL            = "Hello-from-{0}";

    public static final String FILE_PATH_OSX     = "/Users/kg_tech/Desktop/log/{0}.txt";

    public static final String FILE_PATH_WINDOWS = "D:/Users/AHMETPC/Desktop/log/{0}.txt";

    public static final String FILE_PATH_LINUX   = "/vagrant/log/{0}.txt";

    public static OSType getEnvDetail() throws NotFoundException {
        String operSys = System.getProperty("os.name").toLowerCase();

        if (operSys.contains("win")) {
            return OSType.WINDOWS;
        } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
            return OSType.LINUX;
        } else if (operSys.contains("mac") || operSys.contains("darwin")) {
            return OSType.OSX;
        }

        throw new NotFoundException("Running on a platform which is not supported yet");
    }

    public static String getFilePath(int fileName) throws NotFoundException {
        OSType execEnv = getEnvDetail();

        switch (execEnv) {
            case WINDOWS:
                return MessageFormat.format(FILE_PATH_WINDOWS, fileName);
            case OSX:
                return MessageFormat.format(FILE_PATH_OSX, fileName);
            case LINUX:
            default:
                return MessageFormat.format(FILE_PATH_LINUX, fileName);
        }
    }

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
}
