package de.tum.moveii.ops.logbook.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Alexandru Obada on 12/05/16.
 */

/**
 * Used mainly for logging purpose, better exception identification
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALERT_NOT_FOUND("ERR-001", "Alert not found"),
    LOG_NOT_FOUND("ERR-002", "Log not found");

    private String code;
    private String defaultMessage;
}
