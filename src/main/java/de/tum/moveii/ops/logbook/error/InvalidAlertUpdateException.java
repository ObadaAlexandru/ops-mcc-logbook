package de.tum.moveii.ops.logbook.error;

import lombok.NonNull;

import static de.tum.moveii.ops.logbook.error.ErrorCode.INVALID_ALERT_UPDATE;

/**
 * Created by Alexandru Obada on 04/03/17.
 */
public class InvalidAlertUpdateException extends BaseException {
    public InvalidAlertUpdateException(@NonNull String message) {
        super(INVALID_ALERT_UPDATE, message);
    }

    public InvalidAlertUpdateException() {
        super(INVALID_ALERT_UPDATE);
    }
}
