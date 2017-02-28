package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.tum.moveii.ops.logbook.alert.model.AlertSeverity;
import de.tum.moveii.ops.logbook.alert.model.AlertState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertMessage {
    private Long alertId;
    @NotNull
    private AlertSeverity severity;
    @NotNull
    private String subsystem;
    @NotNull
    private String message;
    private AlertState state;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;

    private Long owner;
    private String createdBy;

    @JsonInclude(NON_EMPTY)
    private List<LogMessage> logMessages;
    @JsonInclude(NON_EMPTY)
    private List<NoteMessage> notes;
    @JsonInclude(NON_EMPTY)
    private List<TransitionMessage> transitions;
    @JsonInclude(NON_EMPTY)
    private List<OwnerHistoryMessage> ownerHistory;
}