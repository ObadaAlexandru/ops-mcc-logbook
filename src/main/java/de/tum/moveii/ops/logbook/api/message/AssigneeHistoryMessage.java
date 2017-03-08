package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 11-Feb-17.
 */
@Data
@Builder
public class AssigneeHistoryMessage {
    @NotNull
    private Long oldAssignee;
    @NotNull
    private Long newAssignee;
    @NotNull
    private Long executedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
}
