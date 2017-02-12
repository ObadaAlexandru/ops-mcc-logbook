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
        Log log = new Log();
        log.setSeverity(message.getSeverity());
        log.setSubsystem(message.getSubsystem());
        log.setMessage(message.getMessage());
        log.setCreatedOn(Timestamp.valueOf(message.getCreatedOn()));
        log.setDownloadedOn(Timestamp.valueOf(message.getDownloadedOn()));

        return log;
    }

    @Override
    public LogMessage toMessage(Log resource) {
        LogMessage message = new LogMessage();
        message.setSeverity(resource.getSeverity());
        message.setSubsystem(resource.getSubsystem());
        message.setMessage(resource.getMessage());
        message.setCreatedOn(resource.getCreatedOn().toLocalDateTime());
        message.setDownloadedOn(resource.getDownloadedOn().toLocalDateTime());

        return message;
    }
}
