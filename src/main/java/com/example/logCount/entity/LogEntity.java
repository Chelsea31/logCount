package com.example.logCount.entity;

/*
 * @author Shubham Bansal
 */

import com.example.logCount.models.constants.LogLevel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

@Entity
@Data
@Table(name = "log_entity")
public class LogEntity {
    @Column
    @Temporal(TemporalType.TIME)
    private Date createdAt;

    @Column
    private LogLevel logLevel;

    @Column
    private String data;
}
