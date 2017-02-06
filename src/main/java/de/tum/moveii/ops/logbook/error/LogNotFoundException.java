package de.tum.moveii.ops.logbook.error;

import static de.tum.moveii.ops.logbook.error.ErrorCode.LOG_NOT_FOUND;

/**
 * Created by Cristian Soare on 2/4/17.
 */
public class LogNotFoundException extends BaseException {
    public LogNotFoundException(String message) {
        super(LOG_NOT_FOUND, message);
    }
}
