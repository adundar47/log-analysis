package com.teb.loglistener.service;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teb.loglistener.bus.producer.MessageProducerService;
import com.teb.loglistener.exception.NotFoundException;
import com.teb.loglistener.model.CityNames;
import com.teb.loglistener.model.Log;
import com.teb.loglistener.model.LogType;
import com.teb.loglistener.utils.Utils;

@Component
public class FileWatcher {

    @Autowired
    MessageProducerService messageProducerService;

    static List<String>    lines = new LinkedList<>();

    @PostConstruct
    public void test() throws IOException, InterruptedException, NotFoundException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(Utils.getWorkDir());
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> eventType = event.kind();

                if (eventType == ENTRY_CREATE) {
                    lines = new LinkedList<>();
                } else if (eventType == ENTRY_MODIFY) {
                    fileToLines(event.context().toString());
                }
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }

    }

    private void fileToLines(String fileName) throws IOException, NotFoundException {

        int linesCount = lines.size();
        String filePath = Utils.getFilePath(fileName);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            List<String> readingLines = reader.lines().skip(linesCount)
                    .limit(Utils.countLines(filePath) + 1 - linesCount).collect(Collectors.toList());
            readingLines.stream().forEach(l -> {
                lines.add(l);
                messageProducerService.sendLog(new Log(l.split("=")[1].trim().split(",")[0], LogType.valueOf(l.split("=")[2].trim().split(",")[0]), CityNames.valueOf(l.split("=")[3].trim().split(",")[0]), l.split("=")[4].trim().split("]")[0]));
            });
        } catch (Exception e) {
            System.err.format("Exception: %s%n", e);
        }

    }

}
