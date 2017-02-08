package de.tum.moveii.ops.logbook.log.model;

import de.tum.moveii.ops.logbook.alarm.model.Alert;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by csoare on 2/3/17.
 */
@Entity
@Table(name = "logbook.logmessages")
@Data
public class Log {
    @Id
    @Column(name = "logId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    @Column(name = "severity", nullable = false)
    @Convert(converter = LogSeverityConverter.class)
    private LogSeverity severity;

    @Column(name = "subsystem", nullable = false)
    private String subsystem;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "downloadedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date downloadedOn;

    @ManyToMany(mappedBy="logMessages")
    private List<Alert> alerts;
}
