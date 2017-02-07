package de.tum.moveii.ops.logbook.log.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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
    private String severity;

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
}
