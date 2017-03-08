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
@Table(name = "LOG_MESSAGES", schema = "LOGBOOK")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Log {
    @Id
    @SequenceGenerator(name = "log_messages_log_id_seq",
            sequenceName = "log_messages_log_id_seq",
            schema = "LOGBOOK",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LOG_ID", nullable = false, updatable = false)
    private Long logId;

    @Column(name = "SEVERITY", nullable = false)
    @Convert(converter = LogSeverityConverter.class)
    private LogSeverity severity;

    @Column(name = "SUBSYSTEM", nullable = false)
    private String subsystem;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "DOWNLOADED_ON", nullable = false)
    private LocalDateTime downloadedOn;
}
