package com.example.logCount.orchestration;

/*
 * @author Shubham Bansal
 */

import java.io.IOException;

public class DriverClass {
    OrchestrationObject orchestrationObject = new OrchestrationObject();

    void driver() throws IOException {
        //Need to implemented in a while loop constantly creating tasks for the pool to pick up and execute
        orchestrationObject.orchestrateReading();

        //For multiple log types, will schedule a thread every 1 second
        //TODO
//        orchestrationObject.orchestrateCounting(logLevel, time, maxCount, sleepTime);
//        orchestrationObject.orchestrateCounting(logLevel, time, maxCount, sleepTime);
//        orchestrationObject.orchestrateCounting(logLevel, time, maxCount, sleepTime);
//        orchestrationObject.orchestrateCounting(logLevel, time, maxCount, sleepTime);
//        orchestrationObject.orchestrateCounting(logLevel, time, maxCount, sleepTime);

    }
}
