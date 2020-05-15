package com.example.logCount.repository;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.entity.LogEntity;
import com.example.logCount.object.LogLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findAllByLogLevelAndCreatedAtBetween(LogLevel logLevel, Date startDate, Date endDate);
}
