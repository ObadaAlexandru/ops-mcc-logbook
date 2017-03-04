package de.tum.moveii.ops.logbook.error;

import static de.tum.moveii.ops.logbook.error.ErrorCode.LOG_NOT_FOUND;

/**
 * Created by Alexandru Obada on 04/03/17.
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(LOG_NOT_FOUND, message);
    }
}
