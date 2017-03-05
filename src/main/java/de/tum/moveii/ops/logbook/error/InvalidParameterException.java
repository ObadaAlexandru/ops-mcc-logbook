package de.tum.moveii.ops.logbook.error;

import static de.tum.moveii.ops.logbook.error.ErrorCode.INVALID_PARAMETER;

/**
 * Created by Alexandru Obada on 05/03/17.
 */
public class InvalidParameterException extends BaseException {
    public InvalidParameterException(String message) {
        super(INVALID_PARAMETER, message);
    }
}
