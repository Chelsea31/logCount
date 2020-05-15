package com.example.logCount.object;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.entity.LogEntity;
import com.example.logCount.repository.LogEntityRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

public enum LogLevel {
    INFO {
        @Override
        public List<LogEntity> getLogsForTime(int millis, LogEntityRepository repository) {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.SECOND, millis);
            Date date2 = new Date(instance.toInstant().getEpochSecond());
            Date date = new Date(Instant.now().getEpochSecond());
            return repository.findAllByLogLevelAndCreatedAtBetween(this, date, date2);
        }
    }, WARNING {
        @Override
        public List<LogEntity> getLogsForTime(int millis, LogEntityRepository repository) {
            return null;
        }
    }, CRITICAL {
        @Override
        public List<LogEntity> getLogsForTime(int millis, LogEntityRepository repository) {
            return null;
        }
    }, BLOCKER {
        @Override
        public List<LogEntity> getLogsForTime(int millis, LogEntityRepository repository) {
            return null;
        }
    };

    public abstract List<LogEntity> getLogsForTime(int millis, LogEntityRepository repository);
}
