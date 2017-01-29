package de.tum.moveii.ops.logbook.error;

import static de.tum.moveii.ops.logbook.error.ErrorCode.ALERT_NOT_FOUND;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public class AlertNotFoundException extends BaseException {
    public AlertNotFoundException(String message) {
        super(ALERT_NOT_FOUND, message);
    }
}
