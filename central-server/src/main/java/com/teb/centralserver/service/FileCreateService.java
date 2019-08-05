package com.teb.centralserver.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.teb.centralserver.model.Log;
import com.teb.centralserver.utils.Utils;

@Service
public class FileCreateService {

    BufferedWriter bw;
    int            fileName = 1;

    public void create(Log log) throws IOException {
        String filePath = Utils.getFilePath(fileName);
        bw = new BufferedWriter(new FileWriter(filePath, true));

        File file = new File(filePath);
        if (!file.exists() || !file.isFile())
            return;

        if (2 * 1024 < Utils.getFileSizeKiloBytes(file)) {
            fileName += 1;
            bw = new BufferedWriter(new FileWriter(Utils.getFilePath(fileName), true));
        }

        bw.write(log.toString() + "\n");

        bw.close();
    }

}
