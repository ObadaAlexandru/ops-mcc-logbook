package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.log.model.LogSeverity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Cristian Soare on 2/3/17.
 */
@Data
@Builder
public class LogMessage {
    @NotNull
    private LogSeverity severity;
    @NotNull
    private String subsystem;
    @NotNull
    private String message;
    @NotNull
    private LocalDateTime createdOn;
    @NotNull
    private LocalDateTime downloadedOn;
}
