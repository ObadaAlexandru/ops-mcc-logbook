package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.tum.moveii.ops.logbook.alarm.model.AlertState;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
public class AlertMessage {
    @NotNull
    private String message;
    @NotNull
    private Long owner;
    @NotNull
    private String subsystem;
    private AlertState state;
    @JsonInclude(NON_EMPTY)
    private List<NoteMessage> notes;
    @JsonInclude(NON_EMPTY)
    private List<TransitionMessage> transitions;
}