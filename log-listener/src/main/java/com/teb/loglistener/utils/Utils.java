package com.teb.loglistener.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import com.teb.loglistener.exception.NotFoundException;
import com.teb.loglistener.model.OSType;

public class Utils {

    public static final String WORKDIR_OSX       = "/Users/kg_tech/Desktop/log";

    public static final String WORKDIR_WINDOWS   = "D:/Users/AHMETPC/Desktop/log";

    public static final String WORKDIR_LINUX     = "/vagrant/log";

    public static final String FILE_PATH_OSX     = WORKDIR_OSX + "/{0}";

    public static final String FILE_PATH_WINDOWS = WORKDIR_WINDOWS + "/{0}";

    public static final String FILE_PATH_LINUX   = WORKDIR_LINUX + "/{0}";

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

    public static String getFilePath(String fileName) throws NotFoundException {
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

    public static String getWorkDir() throws NotFoundException {
        OSType execEnv = getEnvDetail();

        switch (execEnv) {
            case WINDOWS:
                return WORKDIR_WINDOWS;
            case OSX:
                return WORKDIR_OSX;
            case LINUX:
            default:
                return WORKDIR_LINUX;
        }
    }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(filename)));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
