package com.example.logCount.orchestration;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.config.UserConfig;

import java.io.IOException;
import java.util.List;

public class DriverClass {
    OrchestrationObject orchestrationObject = new OrchestrationObject();
    //Config list
    List<UserConfig> userConfigList;

    void driver() throws IOException {
        //Need to implemented in a while loop constantly creating tasks for the pool to pick up and execute
        orchestrationObject.orchestrateReading();

        //For multiple log types, will schedule a thread every 1 second
        //TODO
        userConfigList.forEach(user -> {
            user.getLogLevelList().stream().forEach(logLevel -> {
                try {
                    orchestrationObject.orchestrateCounting(logLevel, user.getWindow(), user.getMaxCount(), user.getSleepTime(), user.getNotificationChannelsList());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
