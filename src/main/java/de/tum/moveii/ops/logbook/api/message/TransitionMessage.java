package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.alarm.model.AlertState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
public class TransitionMessage {
    private AlertState from;
    private AlertState to;
    private Long author;
    private LocalDateTime createdOn;
}