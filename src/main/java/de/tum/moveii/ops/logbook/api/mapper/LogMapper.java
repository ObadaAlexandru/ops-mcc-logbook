package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.api.message.LogMessage;
import de.tum.moveii.ops.logbook.log.model.Log;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogMapper implements ResourceMapper<LogMessage, Log> {
    @Override
    public Log toResource(LogMessage message) {
        return Log.builder()
                .logId(message.getLogId())
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .downloadedOn(Timestamp.valueOf(message.getDownloadedOn()))
                .build();
    }

    @Override
    public LogMessage toMessage(Log resource) {
        return LogMessage.builder()
                .logId(resource.getLogId())
                .severity(resource.getSeverity())
                .subsystem(resource.getSubsystem())
                .message(resource.getMessage())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .downloadedOn(resource.getDownloadedOn().toLocalDateTime())
                .build();
    }
}
