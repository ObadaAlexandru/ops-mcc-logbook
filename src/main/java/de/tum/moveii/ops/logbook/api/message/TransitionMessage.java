package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.alert.model.AlertState;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
@Builder
public class TransitionMessage {
    @NotNull
    private Long transitionId;
    @NotNull
    private Long owner;
    @NotNull
    private AlertState startState;
    @NotNull
    private AlertState endState;
    @NotNull
    private LocalDateTime createdOn;
}