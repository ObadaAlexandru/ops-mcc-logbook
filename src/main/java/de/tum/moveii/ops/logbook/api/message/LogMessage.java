package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogMessage {
    private Long logId;
    @NotNull
    private LogSeverity severity;
    @NotNull
    private String subsystem;
    @NotNull
    private String message;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime downloadedOn;
}
