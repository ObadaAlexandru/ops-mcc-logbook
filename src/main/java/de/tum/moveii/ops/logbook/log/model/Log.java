package de.tum.moveii.ops.logbook.log.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by csoare on 2/3/17.
 */
@Entity
@Table(name = "logbook.logmessages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Log {
    @Id
    @SequenceGenerator(name = "logbook.logmessages_logId_seq",
            sequenceName = "logbook.logmessages_logId_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "logId", nullable = false, updatable = false)
    private Long logId;

    @Column(name = "severity", nullable = false)
    @Convert(converter = LogSeverityConverter.class)
    private LogSeverity severity;

    @Column(name = "subsystem", nullable = false)
    private String subsystem;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "downloadedOn", nullable = false)
    private LocalDateTime downloadedOn;
}
