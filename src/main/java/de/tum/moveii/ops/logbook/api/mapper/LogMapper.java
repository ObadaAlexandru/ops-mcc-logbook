package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.api.message.LogMessage;
import de.tum.moveii.ops.logbook.log.model.Log;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogMapper implements ResourceMapper<LogMessage, Log> {
    @Override
    public Log toResource(@NotNull LogMessage message) {
        return Log.builder()
                .logId(message.getLogId())
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .createdOn(message.getCreatedOn())
                .downloadedOn(message.getDownloadedOn())
                .build();
    }

    @Override
    public LogMessage toMessage(@NotNull Log resource) {
        return LogMessage.builder()
                .logId(resource.getLogId())
                .severity(resource.getSeverity())
                .subsystem(resource.getSubsystem())
                .message(resource.getMessage())
                .createdOn(resource.getCreatedOn())
                .downloadedOn(resource.getDownloadedOn())
                .build();
    }
}