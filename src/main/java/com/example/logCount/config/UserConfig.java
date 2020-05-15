package com.example.logCount.config;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.models.constants.LogLevel;
import com.example.logCount.models.constants.NotificationChannels;
import lombok.Data;

import java.util.List;

@Data
public class UserConfig {
    int userId;
    List<NotificationChannels> notificationChannelsList;
    int sleepTime;
    List<LogLevel> logLevelList;
    int window;
    int maxCount;
}
