package com.example.logCount.orchestration;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.entity.LogEntity;
import com.example.logCount.models.LogObject;
import com.example.logCount.models.constants.LogLevel;
import com.example.logCount.models.constants.NotificationChannels;
import com.example.logCount.reader.CustomFileReader;
import com.example.logCount.repository.LogEntityRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class OrchestrationObject {

    @Autowired
    private LogEntityRepository repository;

    private ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    private LogObject readFromFile() throws IOException {
        BufferedReader customFileReader = CustomFileReader.getInstance();
        String payload = customFileReader.readLine();
        //TODO Create LogObject
        return new LogObject();
    }

    private LogEntity changeObjectToEntity(LogObject logObject) {
        LogEntity logEntity = new LogEntity();
        //TODO make logEntity from object
        return logEntity;
    }

    private void saveToDb(LogEntity logEntity) {
        repository.save(logEntity);
    }

    private Runnable createReadTask() {
        Runnable task = new Runnable() {
            @lombok.SneakyThrows
            @Override
            public void run() {
                LogObject logObject = readFromFile();
                LogEntity logEntity = changeObjectToEntity(logObject);
                saveToDb(logEntity);
            }
        };
        return task;
    }

    private Runnable createCountTask(LogLevel logLevel, int window, int maxCount, int sleepTime, List<NotificationChannels> notificationChannels) {
        Runnable task = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                List<LogEntity> logsForTime = logLevel.getLogsForTime(window, repository);
                if (logsForTime.size() > maxCount) {
                    notificationChannels.stream().forEach(notification -> notification.sendNotification());
                    Thread.sleep(sleepTime);
                }
            }
        };
        return task;
    }

    public void orchestrateReading() throws IOException {
        Runnable task = createReadTask();
        executorService.submit(task);
    }

    public void orchestrateCounting(LogLevel logLevel, int window, int maxCount, int sleepTime, List<NotificationChannels> notificationChannels) throws InterruptedException {
        Runnable countTask = createCountTask(logLevel, window, maxCount, sleepTime, notificationChannels);
        scheduledExecutorService.scheduleAtFixedRate(countTask, 0, 1, TimeUnit.SECONDS);
    }

}
