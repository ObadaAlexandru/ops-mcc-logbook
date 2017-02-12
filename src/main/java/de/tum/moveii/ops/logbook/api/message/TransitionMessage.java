package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.alarm.model.AlertState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
@Builder
public class TransitionMessage {
    private AlertState startState;
    private AlertState endState;
    private Long owner;
    private LocalDateTime createdOn;
}