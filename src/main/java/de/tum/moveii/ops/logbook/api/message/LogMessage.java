package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.log.model.LogSeverity;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Created by Cristian Soare on 2/3/17.
 */
@Data
public class LogMessage {
    @NotNull
    private String message;
    @NotNull
    private LogSeverity severity;
    @NotNull
    private String subsystem;
}
