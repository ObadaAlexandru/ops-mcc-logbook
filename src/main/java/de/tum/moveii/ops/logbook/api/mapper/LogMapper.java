package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.log.model.Log;
import de.tum.moveii.ops.logbook.api.message.LogMessage;
import org.springframework.stereotype.Component;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogMapper implements ResourceMapper<LogMessage, Log> {
    @Override
    public Log toResource(LogMessage message) {
        return null;
    }

    @Override
    public LogMessage toMessage(Log resource) { return null; }
}
