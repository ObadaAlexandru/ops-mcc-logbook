package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.log.model.Log;
import de.tum.moveii.ops.logbook.api.message.LogMessage;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogMapper implements ResourceMapper<LogMessage, Log> {
    @Override
    public Log toResource(LogMessage message) {
        Log log = Log.builder()
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .downloadedOn(Timestamp.valueOf(message.getDownloadedOn()))
                .build();

        return log;
    }

    @Override
    public LogMessage toMessage(Log resource) {
        LogMessage logMessage = LogMessage.builder()
                .severity(resource.getSeverity())
                .subsystem(resource.getSubsystem())
                .message(resource.getMessage())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .downloadedOn(resource.getDownloadedOn().toLocalDateTime())
                .build();

        return logMessage;
    }
}
