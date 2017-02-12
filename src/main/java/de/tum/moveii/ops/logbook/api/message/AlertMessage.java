package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.tum.moveii.ops.logbook.alarm.model.AlertSeverity;
import de.tum.moveii.ops.logbook.alarm.model.AlertState;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
@Builder
public class AlertMessage {
    @NotNull
    private AlertSeverity severity;
    @NotNull
    private String subsystem;
    @NotNull
    private String message;
    @NotNull
    private AlertState state;
    @NotNull
    private LocalDateTime createdOn;

    private Long owner;
    private String createdBy;

    @JsonInclude(NON_EMPTY)
    private List<NoteMessage> notes;
    @JsonInclude(NON_EMPTY)
    private List<TransitionMessage> transitions;
    @JsonInclude(NON_EMPTY)
    private List<OwnerHistoryMessage> ownerHistory;
}